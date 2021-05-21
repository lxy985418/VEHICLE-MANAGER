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

import me.zhengjie.modules.system.domain.Driverinfo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.repository.DriverinfoRepository;
import me.zhengjie.modules.system.service.DriverinfoService;
import me.zhengjie.modules.system.service.dto.DriverinfoDto;
import me.zhengjie.modules.system.service.dto.DriverinfoQueryCriteria;
import me.zhengjie.modules.system.service.mapstruct.DriverinfoMapper;
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
public class DriverinfoServiceImpl implements DriverinfoService {

    private final DriverinfoRepository driverinfoRepository;
    private final DriverinfoMapper driverinfoMapper;

    @Override
    public Map<String,Object> queryAll(DriverinfoQueryCriteria criteria, Pageable pageable){
        Page<Driverinfo> page = driverinfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(driverinfoMapper::toDto));
    }

    @Override
    public List<DriverinfoDto> queryAll(DriverinfoQueryCriteria criteria){
        return driverinfoMapper.toDto(driverinfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public DriverinfoDto findById(String driverId) {
        Driverinfo driverinfo = driverinfoRepository.findById(driverId).orElseGet(Driverinfo::new);
        ValidationUtil.isNull(driverinfo.getDriverId(),"Driverinfo","driverId",driverId);
        return driverinfoMapper.toDto(driverinfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DriverinfoDto create(Driverinfo resources) {
        resources.setDriverId(IdUtil.simpleUUID()); 
        return driverinfoMapper.toDto(driverinfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Driverinfo resources) {
        Driverinfo driverinfo = driverinfoRepository.findById(resources.getDriverId()).orElseGet(Driverinfo::new);
        ValidationUtil.isNull( driverinfo.getDriverId(),"Driverinfo","id",resources.getDriverId());
        driverinfo.copy(resources);
        driverinfoRepository.save(driverinfo);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String driverId : ids) {
            driverinfoRepository.deleteById(driverId);
        }
    }

    @Override
    public void download(List<DriverinfoDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (DriverinfoDto driverinfo : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("驾驶员名称", driverinfo.getName());
            map.put("驾驶证类型", driverinfo.getLicTypeNum());
            map.put("性别", driverinfo.getSex());
            map.put("生日", driverinfo.getBirth());
            map.put("地址", driverinfo.getAddress());
            map.put("发证日期", driverinfo.getOpeningDate());
            map.put("下次年检日期", driverinfo.getNextExamine());
            map.put("剩余分值", driverinfo.getSurplusScore());
            map.put("驾驶证状态编号", driverinfo.getLicStatusnum());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}