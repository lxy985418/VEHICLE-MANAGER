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
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author Lee
* @date 2021-04-05
**/
@Entity
@Data
@Table(name="vehicle_info")
public class VehicleInfo implements Serializable {

    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "id")
    private String id;

    @Column(name = "administrative")
    @ApiModelProperty(value = "管理部门")
    private String administrative;

    @Column(name = "liable")
    @ApiModelProperty(value = "责任人")
    private String liable;

    @Column(name = "license")
    @ApiModelProperty(value = "车牌号")
    private String license;

    @Column(name = "engine")
    @ApiModelProperty(value = "发动机号")
    private String engine;

    @Column(name = "identification")
    @ApiModelProperty(value = "车辆识别码")
    private String identification;

    @Column(name = "brand")
    @ApiModelProperty(value = "品牌")
    private String brand;

    @Column(name = "model")
    @ApiModelProperty(value = "型号")
    private String model;

    @Column(name = "registration_date")
    @ApiModelProperty(value = "注册日期")
    private Timestamp registrationDate;

    @Column(name = "driving")
    @ApiModelProperty(value = "行驶证有效日期")
    private String driving;

    @Column(name = "nature")
    @ApiModelProperty(value = "车辆使用性质")
    private String nature;

    @Column(name = "people")
    @ApiModelProperty(value = "核定载客人数")
    private Integer people;

    @Column(name = "color")
    @ApiModelProperty(value = "车体颜色")
    private String color;

    @Column(name = "exhaust")
    @ApiModelProperty(value = "排气量")
    private Integer exhaust;

    @Column(name = "capacity")
    @ApiModelProperty(value = "油箱容量")
    private Float capacity;

    @Column(name = "consumption")
    @ApiModelProperty(value = "理论油耗（百公里）")
    private Float consumption;

    @Column(name = "purchase_time")
    @ApiModelProperty(value = "购车时间")
    private Timestamp purchaseTime;

    @Column(name = "maint_mileage")
    @ApiModelProperty(value = "保养里程数")
    private Float maintMileage;

    @Column(name = "maint_company")
    @ApiModelProperty(value = "保养服务单位")
    private String maintCompany;

    @Column(name = "administ")
    @ApiModelProperty(value = "管理部门")
    private String administ;

    @Column(name = "liable_person")
    @ApiModelProperty(value = "车辆责任人")
    private String liablePerson;

    @Column(name = "year_beginning")
    @ApiModelProperty(value = "年初里程数")
    private Float yearBeginning;

    @Column(name = "year_ending")
    @ApiModelProperty(value = "年终里程数")
    private Float yearEnding;

    @Column(name = "year_mileage")
    @ApiModelProperty(value = "本年里程数")
    private Float yearMileage;

    @Column(name = "years")
    @ApiModelProperty(value = "使用年数")
    private Integer years;

    @Column(name = "accident_num")
    @ApiModelProperty(value = "本年事故次数")
    private Integer accidentNum;

    @Column(name = "maint_num")
    @ApiModelProperty(value = "本年维修次数")
    private Integer maintNum;

    @Column(name = "maint_cost")
    @ApiModelProperty(value = "本年维修总费用")
    private Float maintCost;

    @Column(name = "insurance_date_last")
    @ApiModelProperty(value = "上次保险日期")
    private Timestamp insuranceDateLast;

    @Column(name = "insurance_cost_last")
    @ApiModelProperty(value = "上年保险费用合计")
    private Float insuranceCostLast;

    @Column(name = "insurance_id_last")
    @ApiModelProperty(value = "上年保险单号")
    private String insuranceIdLast;

    @Column(name = "insurance_economy_last")
    @ApiModelProperty(value = "上年保险服务单位")
    private String insuranceEconomyLast;

    @Column(name = "insurance_date")
    @ApiModelProperty(value = "本年保险日期")
    private Timestamp insuranceDate;

    @Column(name = "insurance_cost")
    @ApiModelProperty(value = "本年保险费用合计")
    private Float insuranceCost;

    @Column(name = "insurance_id")
    @ApiModelProperty(value = "本年保险单号")
    private String insuranceId;

    @Column(name = "insurance_economy")
    @ApiModelProperty(value = "本年保险单位")
    private String insuranceEconomy;

    @Column(name = "inspection_date_last")
    @ApiModelProperty(value = "上次年检日期")
    private Timestamp inspectionDateLast;

    @Column(name = "inspection_year")
    @ApiModelProperty(value = "检车时限")
    private Integer inspectionYear;

    @Column(name = "inspection_cost_last")
    @ApiModelProperty(value = "上次检车费用")
    private Float inspectionCostLast;

    @Column(name = "inspection_date")
    @ApiModelProperty(value = "下次年检日期")
    private Timestamp inspectionDate;

    @Column(name = "inspection_time")
    @ApiModelProperty(value = "是否按时年检")
    private Integer inspectionTime;

    @Column(name = "inspection_cost")
    @ApiModelProperty(value = "本次检车费用")
    private Float inspectionCost;

    @Column(name = "vehicle_condition")
    @ApiModelProperty(value = "车况")
    private String vehicleCondition;

    public void copy(VehicleInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}