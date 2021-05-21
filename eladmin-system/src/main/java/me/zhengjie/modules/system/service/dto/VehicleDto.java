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
package me.zhengjie.modules.system.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author Lee
* @date 2021-03-29
**/
@Data
public class VehicleDto implements Serializable {

    /** 车辆序号 */
    private String vehicleId;

    /** 车辆信息 */
    private String vehicleType;

    /** 车架号 */
    private String vin;

    /** 厂牌型号 */
    private String vehicleModel;

    /** 产地 */
    private String origin;

    /** 底盘 */
    private String chassis;

    /** 客车类型等级 */
    private String busType;

    /** 外廓长度 */
    private Double vehicleLength;

    /** 外廓宽度 */
    private Double vehicleWidth;

    /** 外廓高度 */
    private Double vehicleHight;

    /** 总质量 */
    private Double vehicleWeight;

    /** 座/铺位排列 */
    private Integer vehicleArrangement;

    /** 乘员数 */
    private Integer vehicleNum;

    /** 牵引总质量 */
    private Double totalMass;

    /** 车轴数 */
    private Integer axle;

    /** 发动机厂牌型号 */
    private String engineModele;

    /** 发动机号码 */
    private String engineId;

    /** 燃料种类 */
    private String fuelType;

    /** 发动机功率 */
    private Integer enginePower;

    /** 发动机排量 */
    private Integer engineDisplacement;

    /** 排放标准 */
    private Integer emissionStandard;

    /** 驱动形式 */
    private String drivingForm;

    /** 轮胎数 */
    private Integer tireNum;

    /** 前照灯制式 */
    private String headlampSystem;

    /** 变速器形式 */
    private Integer transmissionForm;

    /** 缓速器 */
    private Integer retarder;

    /** 转向器 */
    private Integer steeringGear;

    /** 行车制动形式 */
    private Integer serviceBrakingMode;

    /** 前轮行车制动形式 */
    private Integer beforeBreaking;

    /** 后轮单双回路 */
    private Integer rearBreaking;

    /** 前轮悬挂形式 */
    private Integer suspensionFormBefore;

    /** 前轮（气囊/片板簧） */
    private Integer suspensionFormBefore1;

    /** 后轮悬挂形式 */
    private Integer suspensionFormRear;

    /** 后轮（气囊/片板簧） */
    private Integer suspensionFormRear1;

    /** 其他配置 */
    private String otherConfigurations;

    /** 随车物品 */
    private String itemsonBoard;
}