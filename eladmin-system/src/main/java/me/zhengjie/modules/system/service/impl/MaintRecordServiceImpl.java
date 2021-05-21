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

import me.zhengjie.modules.system.domain.MaintRecord;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.repository.MaintRecordRepository;
import me.zhengjie.modules.system.service.MaintRecordService;
import me.zhengjie.modules.system.service.dto.MaintRecordDto;
import me.zhengjie.modules.system.service.dto.MaintRecordQueryCriteria;
import me.zhengjie.modules.system.service.mapstruct.MaintRecordMapper;
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
* @date 2021-04-02
**/
@Service
@RequiredArgsConstructor
public class MaintRecordServiceImpl implements MaintRecordService {

    private final MaintRecordRepository maintRecordRepository;
    private final MaintRecordMapper maintRecordMapper;

    @Override
    public Map<String,Object> queryAll(MaintRecordQueryCriteria criteria, Pageable pageable){
        Page<MaintRecord> page = maintRecordRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(maintRecordMapper::toDto));
    }

    @Override
    public List<MaintRecordDto> queryAll(MaintRecordQueryCriteria criteria){
        return maintRecordMapper.toDto(maintRecordRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public MaintRecordDto findById(String id) {
        MaintRecord maintRecord = maintRecordRepository.findById(id).orElseGet(MaintRecord::new);
        ValidationUtil.isNull(maintRecord.getId(),"MaintRecord","id",id);
        return maintRecordMapper.toDto(maintRecord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MaintRecordDto create(MaintRecord resources) {
        resources.setId(IdUtil.simpleUUID()); 
        return maintRecordMapper.toDto(maintRecordRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(MaintRecord resources) {
        MaintRecord maintRecord = maintRecordRepository.findById(resources.getId()).orElseGet(MaintRecord::new);
        ValidationUtil.isNull( maintRecord.getId(),"MaintRecord","id",resources.getId());
        maintRecord.copy(resources);
        maintRecordRepository.save(maintRecord);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String id : ids) {
            maintRecordRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<MaintRecordDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (MaintRecordDto maintRecord : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("登记日期", maintRecord.getRegistrationDate());
            map.put("车牌", maintRecord.getLicense());
            map.put("维修类别", maintRecord.getMaintenance());
            map.put("名称", maintRecord.getDesignation());
            map.put("规格", maintRecord.getSpecification());
            map.put("数量", maintRecord.getNum());
            map.put("价格", maintRecord.getPrice());
            map.put("厂家", maintRecord.getManufacturer());
            map.put("登记人", maintRecord.getRegistrant());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}