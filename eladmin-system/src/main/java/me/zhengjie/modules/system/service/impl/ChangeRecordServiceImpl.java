/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package me.zhengjie.modules.system.service.impl;

import me.zhengjie.modules.system.domain.ChangeRecord;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.repository.ChangeRecordRepository;
import me.zhengjie.modules.system.service.ChangeRecordService;
import me.zhengjie.modules.system.service.dto.ChangeRecordDto;
import me.zhengjie.modules.system.service.dto.ChangeRecordQueryCriteria;
import me.zhengjie.modules.system.service.mapstruct.ChangeRecordMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @website https://el-admin.vip
* @description 服务实现
* @author Lee
* @date 2021-03-31
**/
@Service
@RequiredArgsConstructor
public class ChangeRecordServiceImpl implements ChangeRecordService {

    private final ChangeRecordRepository changeRecordRepository;
    private final ChangeRecordMapper changeRecordMapper;

    @Override
    public Map<String,Object> queryAll(ChangeRecordQueryCriteria criteria, Pageable pageable){
        Page<ChangeRecord> page = changeRecordRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(changeRecordMapper::toDto));
    }

    @Override
    public List<ChangeRecordDto> queryAll(ChangeRecordQueryCriteria criteria){
        return changeRecordMapper.toDto(changeRecordRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ChangeRecordDto findById(String id) {
        ChangeRecord changeRecord = changeRecordRepository.findById(id).orElseGet(ChangeRecord::new);
        ValidationUtil.isNull(changeRecord.getId(),"ChangeRecord","id",id);
        return changeRecordMapper.toDto(changeRecord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ChangeRecordDto create(ChangeRecord resources) {
        resources.setId(IdUtil.simpleUUID()); 
        return changeRecordMapper.toDto(changeRecordRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ChangeRecord resources) {
        ChangeRecord changeRecord = changeRecordRepository.findById(resources.getId()).orElseGet(ChangeRecord::new);
        ValidationUtil.isNull( changeRecord.getId(),"ChangeRecord","id",resources.getId());
        changeRecord.copy(resources);
        changeRecordRepository.save(changeRecord);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String id : ids) {
            changeRecordRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<ChangeRecordDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ChangeRecordDto changeRecord : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("变更日期", changeRecord.getChangeDate());
            map.put("变更原因", changeRecord.getChangeReason());
            map.put("变更事项", changeRecord.getChanges());
            map.put("登记人", changeRecord.getRegistrant());
            map.put("变更前车牌", changeRecord.getBefore());
            map.put("变更后车牌", changeRecord.getAfter());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}