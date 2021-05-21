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

import me.zhengjie.modules.system.domain.Vehicle;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.repository.VehicleRepository;
import me.zhengjie.modules.system.service.VehicleService;
import me.zhengjie.modules.system.service.dto.VehicleDto;
import me.zhengjie.modules.system.service.dto.VehicleQueryCriteria;
import me.zhengjie.modules.system.service.mapstruct.VehicleMapper;
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
* @date 2021-03-29
**/
@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    public Map<String,Object> queryAll(VehicleQueryCriteria criteria, Pageable pageable){
        Page<Vehicle> page = vehicleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(vehicleMapper::toDto));
    }

    @Override
    public List<VehicleDto> queryAll(VehicleQueryCriteria criteria){
        return vehicleMapper.toDto(vehicleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public VehicleDto findById(String vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseGet(Vehicle::new);
        ValidationUtil.isNull(vehicle.getVehicleId(),"Vehicle","vehicleId",vehicleId);
        return vehicleMapper.toDto(vehicle);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VehicleDto create(Vehicle resources) {
        resources.setVehicleId(IdUtil.simpleUUID()); 
        return vehicleMapper.toDto(vehicleRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Vehicle resources) {
        Vehicle vehicle = vehicleRepository.findById(resources.getVehicleId()).orElseGet(Vehicle::new);
        ValidationUtil.isNull( vehicle.getVehicleId(),"Vehicle","id",resources.getVehicleId());
        vehicle.copy(resources);
        vehicleRepository.save(vehicle);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String vehicleId : ids) {
            vehicleRepository.deleteById(vehicleId);
        }
    }

    @Override
    public void download(List<VehicleDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (VehicleDto vehicle : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("车辆信息", vehicle.getVehicleType());
            map.put("车架号", vehicle.getVin());
            map.put("厂牌型号", vehicle.getVehicleModel());
            map.put("产地", vehicle.getOrigin());
            map.put("底盘", vehicle.getChassis());
            map.put("客车类型等级", vehicle.getBusType());
            map.put("外廓长度", vehicle.getVehicleLength());
            map.put("外廓宽度", vehicle.getVehicleWidth());
            map.put("外廓高度", vehicle.getVehicleHight());
            map.put("总质量", vehicle.getVehicleWeight());
            map.put("座/铺位排列", vehicle.getVehicleArrangement());
            map.put("乘员数", vehicle.getVehicleNum());
            map.put("牵引总质量", vehicle.getTotalMass());
            map.put("车轴数", vehicle.getAxle());
            map.put("发动机厂牌型号", vehicle.getEngineModele());
            map.put("发动机号码", vehicle.getEngineId());
            map.put("燃料种类", vehicle.getFuelType());
            map.put("发动机功率", vehicle.getEnginePower());
            map.put("发动机排量", vehicle.getEngineDisplacement());
            map.put("排放标准", vehicle.getEmissionStandard());
            map.put("驱动形式", vehicle.getDrivingForm());
            map.put("轮胎数", vehicle.getTireNum());
            map.put("前照灯制式", vehicle.getHeadlampSystem());
            map.put("变速器形式", vehicle.getTransmissionForm());
            map.put("缓速器", vehicle.getRetarder());
            map.put("转向器", vehicle.getSteeringGear());
            map.put("行车制动形式", vehicle.getServiceBrakingMode());
            map.put("前轮行车制动形式", vehicle.getBeforeBreaking());
            map.put("后轮单双回路", vehicle.getRearBreaking());
            map.put("前轮悬挂形式", vehicle.getSuspensionFormBefore());
            map.put("前轮（气囊/片板簧）", vehicle.getSuspensionFormBefore1());
            map.put("后轮悬挂形式", vehicle.getSuspensionFormRear());
            map.put("后轮（气囊/片板簧）", vehicle.getSuspensionFormRear1());
            map.put("其他配置", vehicle.getOtherConfigurations());
            map.put("随车物品", vehicle.getItemsonBoard());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}