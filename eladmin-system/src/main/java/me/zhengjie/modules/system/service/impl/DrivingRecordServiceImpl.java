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

import me.zhengjie.modules.system.domain.DrivingRecord;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.repository.DrivingRecordRepository;
import me.zhengjie.modules.system.service.DrivingRecordService;
import me.zhengjie.modules.system.service.dto.DrivingRecordDto;
import me.zhengjie.modules.system.service.dto.DrivingRecordQueryCriteria;
import me.zhengjie.modules.system.service.mapstruct.DrivingRecordMapper;
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
* @date 2021-04-07
**/
@Service
@RequiredArgsConstructor
public class DrivingRecordServiceImpl implements DrivingRecordService {

    private final DrivingRecordRepository drivingRecordRepository;
    private final DrivingRecordMapper drivingRecordMapper;

    @Override
    public Map<String,Object> queryAll(DrivingRecordQueryCriteria criteria, Pageable pageable){
        Page<DrivingRecord> page = drivingRecordRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(drivingRecordMapper::toDto));
    }

    @Override
    public List<DrivingRecordDto> queryAll(DrivingRecordQueryCriteria criteria){
        return drivingRecordMapper.toDto(drivingRecordRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public DrivingRecordDto findById(String drivingId) {
        DrivingRecord drivingRecord = drivingRecordRepository.findById(drivingId).orElseGet(DrivingRecord::new);
        ValidationUtil.isNull(drivingRecord.getDrivingId(),"DrivingRecord","drivingId",drivingId);
        return drivingRecordMapper.toDto(drivingRecord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DrivingRecordDto create(DrivingRecord resources) {
        resources.setDrivingId(IdUtil.simpleUUID()); 
        return drivingRecordMapper.toDto(drivingRecordRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DrivingRecord resources) {
        DrivingRecord drivingRecord = drivingRecordRepository.findById(resources.getDrivingId()).orElseGet(DrivingRecord::new);
        ValidationUtil.isNull( drivingRecord.getDrivingId(),"DrivingRecord","id",resources.getDrivingId());
        drivingRecord.copy(resources);
        drivingRecordRepository.save(drivingRecord);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String drivingId : ids) {
            drivingRecordRepository.deleteById(drivingId);
        }
    }

    @Override
    public void download(List<DrivingRecordDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (DrivingRecordDto drivingRecord : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("出车司机", drivingRecord.getDrivingDiver());
            map.put("出车事由", drivingRecord.getDrivingReason());
            map.put("出车时间", drivingRecord.getDrivingDate());
            map.put("预计归车时间", drivingRecord.getDrivingEndDate());
            map.put("备注", drivingRecord.getDrivingRemarks());
            map.put("车牌", drivingRecord.getDrivingLicense());
            map.put("出车地点", drivingRecord.getDrivingBegging());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}