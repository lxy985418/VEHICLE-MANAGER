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
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author Lee
* @date 2021-04-05
**/
@Data
public class VehicleInfoDto implements Serializable {

    private String id;

    /** 管理部门 */
    private String administrative;

    /** 责任人 */
    private String liable;

    /** 车牌号 */
    private String license;

    /** 发动机号 */
    private String engine;

    /** 车辆识别码 */
    private String identification;

    /** 品牌 */
    private String brand;

    /** 型号 */
    private String model;

    /** 注册日期 */
    private Timestamp registrationDate;

    /** 行驶证有效日期 */
    private String driving;

    /** 车辆使用性质 */
    private String nature;

    /** 核定载客人数 */
    private Integer people;

    /** 车体颜色 */
    private String color;

    /** 排气量 */
    private Integer exhaust;

    /** 油箱容量 */
    private Float capacity;

    /** 理论油耗（百公里） */
    private Float consumption;

    /** 购车时间 */
    private Timestamp purchaseTime;

    /** 保养里程数 */
    private Float maintMileage;

    /** 保养服务单位 */
    private String maintCompany;

    /** 管理部门 */
    private String administ;

    /** 车辆责任人 */
    private String liablePerson;

    /** 年初里程数 */
    private Float yearBeginning;

    /** 年终里程数 */
    private Float yearEnding;

    /** 本年里程数 */
    private Float yearMileage;

    /** 使用年数 */
    private Integer years;

    /** 本年事故次数 */
    private Integer accidentNum;

    /** 本年维修次数 */
    private Integer maintNum;

    /** 本年维修总费用 */
    private Float maintCost;

    /** 上次保险日期 */
    private Timestamp insuranceDateLast;

    /** 上年保险费用合计 */
    private Float insuranceCostLast;

    /** 上年保险单号 */
    private String insuranceIdLast;

    /** 上年保险服务单位 */
    private String insuranceEconomyLast;

    /** 本年保险日期 */
    private Timestamp insuranceDate;

    /** 本年保险费用合计 */
    private Float insuranceCost;

    /** 本年保险单号 */
    private String insuranceId;

    /** 本年保险单位 */
    private String insuranceEconomy;

    /** 上次年检日期 */
    private Timestamp inspectionDateLast;

    /** 检车时限 */
    private Integer inspectionYear;

    /** 上次检车费用 */
    private Float inspectionCostLast;

    /** 下次年检日期 */
    private Timestamp inspectionDate;

    /** 是否按时年检 */
    private Integer inspectionTime;

    /** 本次检车费用 */
    private Float inspectionCost;

    /** 车况 */
    private String vehicleCondition;
}