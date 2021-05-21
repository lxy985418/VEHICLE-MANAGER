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

import me.zhengjie.modules.system.domain.VehicleInfo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.repository.VehicleInfoRepository;
import me.zhengjie.modules.system.service.VehicleInfoService;
import me.zhengjie.modules.system.service.dto.VehicleInfoDto;
import me.zhengjie.modules.system.service.dto.VehicleInfoQueryCriteria;
import me.zhengjie.modules.system.service.mapstruct.VehicleInfoMapper;
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
* @date 2021-04-05
**/
@Service
@RequiredArgsConstructor
public class VehicleInfoServiceImpl implements VehicleInfoService {

    private final VehicleInfoRepository vehicleInfoRepository;
    private final VehicleInfoMapper vehicleInfoMapper;

    @Override
    public Map<String,Object> queryAll(VehicleInfoQueryCriteria criteria, Pageable pageable){
        Page<VehicleInfo> page = vehicleInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(vehicleInfoMapper::toDto));
    }

    @Override
    public List<VehicleInfoDto> queryAll(VehicleInfoQueryCriteria criteria){
        return vehicleInfoMapper.toDto(vehicleInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public VehicleInfoDto findById(String id) {
        VehicleInfo vehicleInfo = vehicleInfoRepository.findById(id).orElseGet(VehicleInfo::new);
        ValidationUtil.isNull(vehicleInfo.getId(),"VehicleInfo","id",id);
        return vehicleInfoMapper.toDto(vehicleInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VehicleInfoDto create(VehicleInfo resources) {
        resources.setId(IdUtil.simpleUUID()); 
        return vehicleInfoMapper.toDto(vehicleInfoRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(VehicleInfo resources) {
        VehicleInfo vehicleInfo = vehicleInfoRepository.findById(resources.getId()).orElseGet(VehicleInfo::new);
        ValidationUtil.isNull( vehicleInfo.getId(),"VehicleInfo","id",resources.getId());
        vehicleInfo.copy(resources);
        vehicleInfoRepository.save(vehicleInfo);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String id : ids) {
            vehicleInfoRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<VehicleInfoDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (VehicleInfoDto vehicleInfo : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("管理部门", vehicleInfo.getAdministrative());
            map.put("责任人", vehicleInfo.getLiable());
            map.put("车牌号", vehicleInfo.getLicense());
            map.put("发动机号", vehicleInfo.getEngine());
            map.put("车辆识别码", vehicleInfo.getIdentification());
            map.put("品牌", vehicleInfo.getBrand());
            map.put("型号", vehicleInfo.getModel());
            map.put("注册日期", vehicleInfo.getRegistrationDate());
            map.put("行驶证有效日期", vehicleInfo.getDriving());
            map.put("车辆使用性质", vehicleInfo.getNature());
            map.put("核定载客人数", vehicleInfo.getPeople());
            map.put("车体颜色", vehicleInfo.getColor());
            map.put("排气量", vehicleInfo.getExhaust());
            map.put("油箱容量", vehicleInfo.getCapacity());
            map.put("理论油耗（百公里）", vehicleInfo.getConsumption());
            map.put("购车时间", vehicleInfo.getPurchaseTime());
            map.put("保养里程数", vehicleInfo.getMaintMileage());
            map.put("保养服务单位", vehicleInfo.getMaintCompany());
            map.put("管理部门", vehicleInfo.getAdminist());
            map.put("车辆责任人", vehicleInfo.getLiablePerson());
            map.put("年初里程数", vehicleInfo.getYearBeginning());
            map.put("年终里程数", vehicleInfo.getYearEnding());
            map.put("本年里程数", vehicleInfo.getYearMileage());
            map.put("使用年数", vehicleInfo.getYears());
            map.put("本年事故次数", vehicleInfo.getAccidentNum());
            map.put("本年维修次数", vehicleInfo.getMaintNum());
            map.put("本年维修总费用", vehicleInfo.getMaintCost());
            map.put("上次保险日期", vehicleInfo.getInsuranceDateLast());
            map.put("上年保险费用合计", vehicleInfo.getInsuranceCostLast());
            map.put("上年保险单号", vehicleInfo.getInsuranceIdLast());
            map.put("上年保险服务单位", vehicleInfo.getInsuranceEconomyLast());
            map.put("本年保险日期", vehicleInfo.getInsuranceDate());
            map.put("本年保险费用合计", vehicleInfo.getInsuranceCost());
            map.put("本年保险单号", vehicleInfo.getInsuranceId());
            map.put("本年保险单位", vehicleInfo.getInsuranceEconomy());
            map.put("上次年检日期", vehicleInfo.getInspectionDateLast());
            map.put("检车时限", vehicleInfo.getInspectionYear());
            map.put("上次检车费用", vehicleInfo.getInspectionCostLast());
            map.put("下次年检日期", vehicleInfo.getInspectionDate());
            map.put("是否按时年检", vehicleInfo.getInspectionTime());
            map.put("本次检车费用", vehicleInfo.getInspectionCost());
            map.put("车况", vehicleInfo.getVehicleCondition());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}