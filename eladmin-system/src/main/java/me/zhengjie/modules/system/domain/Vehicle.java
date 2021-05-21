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
package me.zhengjie.modules.system.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author Lee
* @date 2021-03-29
**/
@Entity
@Data
@Table(name="vehicle")
public class Vehicle implements Serializable {

    @Id
    @Column(name = "vehicle_id")
    @ApiModelProperty(value = "车辆序号")
    private String vehicleId;

    @Column(name = "vehicle_type")
    @ApiModelProperty(value = "车辆信息")
    private String vehicleType;

    @Column(name = "VIN")
    @ApiModelProperty(value = "车架号")
    private String vin;

    @Column(name = "vehicle_model")
    @ApiModelProperty(value = "厂牌型号")
    private String vehicleModel;

    @Column(name = "origin")
    @ApiModelProperty(value = "产地")
    private String origin;

    @Column(name = "chassis")
    @ApiModelProperty(value = "底盘")
    private String chassis;

    @Column(name = "bus_type")
    @ApiModelProperty(value = "客车类型等级")
    private String busType;

    @Column(name = "vehicle_length")
    @ApiModelProperty(value = "外廓长度")
    private Double vehicleLength;

    @Column(name = "vehicle_width")
    @ApiModelProperty(value = "外廓宽度")
    private Double vehicleWidth;

    @Column(name = "vehicle_hight")
    @ApiModelProperty(value = "外廓高度")
    private Double vehicleHight;

    @Column(name = "vehicle_weight")
    @ApiModelProperty(value = "总质量")
    private Double vehicleWeight;

    @Column(name = "vehicle_arrangement")
    @ApiModelProperty(value = "座/铺位排列")
    private Integer vehicleArrangement;

    @Column(name = "vehicle_num")
    @ApiModelProperty(value = "乘员数")
    private Integer vehicleNum;

    @Column(name = "total_mass")
    @ApiModelProperty(value = "牵引总质量")
    private Double totalMass;

    @Column(name = "axle")
    @ApiModelProperty(value = "车轴数")
    private Integer axle;

    @Column(name = "engine_modele")
    @ApiModelProperty(value = "发动机厂牌型号")
    private String engineModele;

    @Column(name = "engine_id")
    @ApiModelProperty(value = "发动机号码")
    private String engineId;

    @Column(name = "fuel_type")
    @ApiModelProperty(value = "燃料种类")
    private String fuelType;

    @Column(name = "engine_power")
    @ApiModelProperty(value = "发动机功率")
    private Integer enginePower;

    @Column(name = "engine_displacement")
    @ApiModelProperty(value = "发动机排量")
    private Integer engineDisplacement;

    @Column(name = "emission_standard")
    @ApiModelProperty(value = "排放标准")
    private Integer emissionStandard;

    @Column(name = "driving_form")
    @ApiModelProperty(value = "驱动形式")
    private String drivingForm;

    @Column(name = "tire_num")
    @ApiModelProperty(value = "轮胎数")
    private Integer tireNum;

    @Column(name = "headlamp_system")
    @ApiModelProperty(value = "前照灯制式")
    private String headlampSystem;

    @Column(name = "transmission_form")
    @ApiModelProperty(value = "变速器形式")
    private Integer transmissionForm;

    @Column(name = "retarder")
    @ApiModelProperty(value = "缓速器")
    private Integer retarder;

    @Column(name = "steering_gear")
    @ApiModelProperty(value = "转向器")
    private Integer steeringGear;

    @Column(name = "service_braking_mode")
    @ApiModelProperty(value = "行车制动形式")
    private Integer serviceBrakingMode;

    @Column(name = "before_breaking")
    @ApiModelProperty(value = "前轮行车制动形式")
    private Integer beforeBreaking;

    @Column(name = "rear_breaking")
    @ApiModelProperty(value = "后轮单双回路")
    private Integer rearBreaking;

    @Column(name = "suspension_form_before")
    @ApiModelProperty(value = "前轮悬挂形式")
    private Integer suspensionFormBefore;

    @Column(name = "suspension_form_before1")
    @ApiModelProperty(value = "前轮（气囊/片板簧）")
    private Integer suspensionFormBefore1;

    @Column(name = "suspension_form_rear")
    @ApiModelProperty(value = "后轮悬挂形式")
    private Integer suspensionFormRear;

    @Column(name = "suspension_form_rear1")
    @ApiModelProperty(value = "后轮（气囊/片板簧）")
    private Integer suspensionFormRear1;

    @Column(name = "other_configurations")
    @ApiModelProperty(value = "其他配置")
    private String otherConfigurations;

    @Column(name = "itemson_board")
    @ApiModelProperty(value = "随车物品")
    private String itemsonBoard;

    public void copy(Vehicle source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}