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

import me.zhengjie.modules.system.domain.Fuel;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.repository.FuelRepository;
import me.zhengjie.modules.system.service.FuelService;
import me.zhengjie.modules.system.service.dto.FuelDto;
import me.zhengjie.modules.system.service.dto.FuelQueryCriteria;
import me.zhengjie.modules.system.service.mapstruct.FuelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
public class FuelServiceImpl implements FuelService {

    private final FuelRepository fuelRepository;
    private final FuelMapper fuelMapper;

    @Override
    public Map<String,Object> queryAll(FuelQueryCriteria criteria, Pageable pageable){
        Page<Fuel> page = fuelRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(fuelMapper::toDto));
    }

    @Override
    public List<FuelDto> queryAll(FuelQueryCriteria criteria){
        return fuelMapper.toDto(fuelRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public FuelDto findById(Integer oddNum) {
        Fuel fuel = fuelRepository.findById(oddNum).orElseGet(Fuel::new);
        ValidationUtil.isNull(fuel.getOddNum(),"Fuel","oddNum",oddNum);
        return fuelMapper.toDto(fuel);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FuelDto create(Fuel resources) {
        return fuelMapper.toDto(fuelRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Fuel resources) {
        Fuel fuel = fuelRepository.findById(resources.getOddNum()).orElseGet(Fuel::new);
        ValidationUtil.isNull( fuel.getOddNum(),"Fuel","id",resources.getOddNum());
        fuel.copy(resources);
        fuelRepository.save(fuel);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer oddNum : ids) {
            fuelRepository.deleteById(oddNum);
        }
    }

    @Override
    public void download(List<FuelDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (FuelDto fuel : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("序号", fuel.getNum());
            map.put("充值日期", fuel.getDate());
            map.put("银行卡号", fuel.getCartId());
            map.put("车牌号", fuel.getLic());
            map.put("充值金额", fuel.getMoney());
            map.put("备注", fuel.getRemarks());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}