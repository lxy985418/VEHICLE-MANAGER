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

import me.zhengjie.modules.system.domain.CarCollect;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.repository.CarCollectRepository;
import me.zhengjie.modules.system.service.CarCollectService;
import me.zhengjie.modules.system.service.dto.CarCollectDto;
import me.zhengjie.modules.system.service.dto.CarCollectQueryCriteria;
import me.zhengjie.modules.system.service.mapstruct.CarCollectMapper;
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
* @date 2021-05-02
**/
@Service
@RequiredArgsConstructor
public class CarCollectServiceImpl implements CarCollectService {

    private final CarCollectRepository carCollectRepository;
    private final CarCollectMapper carCollectMapper;

    @Override
    public Map<String,Object> queryAll(CarCollectQueryCriteria criteria, Pageable pageable){
        Page<CarCollect> page = carCollectRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(carCollectMapper::toDto));
    }

    @Override
    public List<CarCollectDto> queryAll(CarCollectQueryCriteria criteria){
        return carCollectMapper.toDto(carCollectRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public CarCollectDto findById(String carCollectId) {
        CarCollect carCollect = carCollectRepository.findById(carCollectId).orElseGet(CarCollect::new);
        ValidationUtil.isNull(carCollect.getCarCollectId(),"CarCollect","carCollectId",carCollectId);
        return carCollectMapper.toDto(carCollect);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CarCollectDto create(CarCollect resources) {
        resources.setCarCollectId(IdUtil.simpleUUID()); 
        return carCollectMapper.toDto(carCollectRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CarCollect resources) {
        CarCollect carCollect = carCollectRepository.findById(resources.getCarCollectId()).orElseGet(CarCollect::new);
        ValidationUtil.isNull( carCollect.getCarCollectId(),"CarCollect","id",resources.getCarCollectId());
        carCollect.copy(resources);
        carCollectRepository.save(carCollect);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String carCollectId : ids) {
            carCollectRepository.deleteById(carCollectId);
        }
    }

    @Override
    public void download(List<CarCollectDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (CarCollectDto carCollect : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("出行单号", carCollect.getDrivingId());
            map.put("出车地点", carCollect.getCarCollectBegging());
            map.put("收车地点", carCollect.getCarCollectEndding());
            map.put("出车时间", carCollect.getCarCollectBegindate());
            map.put("收车时间", carCollect.getCarCollectEnddate());
            map.put("出行距离", carCollect.getCarCollectDistance());
            map.put("司机", carCollect.getCarCollectDriver());
            map.put("备注", carCollect.getCarCollectRemark());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}