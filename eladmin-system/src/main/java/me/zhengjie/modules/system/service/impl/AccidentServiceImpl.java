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

import me.zhengjie.modules.system.domain.Accident;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.repository.AccidentRepository;
import me.zhengjie.modules.system.service.AccidentService;
import me.zhengjie.modules.system.service.dto.AccidentDto;
import me.zhengjie.modules.system.service.dto.AccidentQueryCriteria;
import me.zhengjie.modules.system.service.mapstruct.AccidentMapper;
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
* @date 2021-04-01
**/
@Service
@RequiredArgsConstructor
public class AccidentServiceImpl implements AccidentService {

    private final AccidentRepository accidentRepository;
    private final AccidentMapper accidentMapper;

    @Override
    public Map<String,Object> queryAll(AccidentQueryCriteria criteria, Pageable pageable){
        Page<Accident> page = accidentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(accidentMapper::toDto));
    }

    @Override
    public List<AccidentDto> queryAll(AccidentQueryCriteria criteria){
        return accidentMapper.toDto(accidentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public AccidentDto findById(String accidentId) {
        Accident accident = accidentRepository.findById(accidentId).orElseGet(Accident::new);
        ValidationUtil.isNull(accident.getAccidentId(),"Accident","accidentId",accidentId);
        return accidentMapper.toDto(accident);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccidentDto create(Accident resources) {
        resources.setAccidentId(IdUtil.simpleUUID()); 
        return accidentMapper.toDto(accidentRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Accident resources) {
        Accident accident = accidentRepository.findById(resources.getAccidentId()).orElseGet(Accident::new);
        ValidationUtil.isNull( accident.getAccidentId(),"Accident","id",resources.getAccidentId());
        accident.copy(resources);
        accidentRepository.save(accident);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String accidentId : ids) {
            accidentRepository.deleteById(accidentId);
        }
    }

    @Override
    public void download(List<AccidentDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (AccidentDto accident : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("事故日期", accident.getAccidentDate());
            map.put("事故地点", accident.getAccidentPlace());
            map.put("事故性质", accident.getAccidentNature());
            map.put("事故类别", accident.getAccidentKind());
            map.put("直接经济损失", accident.getEconomyLoss());
            map.put("登记人", accident.getRegistrant());
            map.put("事故损失", accident.getAccidentLoss());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}