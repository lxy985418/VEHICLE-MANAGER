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
* @date 2021-04-02
**/
@Entity
@Data
@Table(name="reimbursement")
public class Reimbursement implements Serializable {

    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "报销单ID")
    private String id;

    @Column(name = "depart")
    @ApiModelProperty(value = "报销部门")
    private String depart;

    @Column(name = "date")
    @ApiModelProperty(value = "报销日期")
    private Timestamp date;

    @Column(name = "license")
    @ApiModelProperty(value = "车牌号")
    private String license;

    @Column(name = "driver")
    @ApiModelProperty(value = "驾驶员")
    private String driver;

    @Column(name = "Informant")
    @ApiModelProperty(value = "呈报人")
    private String informant;

    @Column(name = "ride_date")
    @ApiModelProperty(value = "乘车日期")
    private Timestamp rideDate;

    @Column(name = "begin")
    @ApiModelProperty(value = "起始地址")
    private String begin;

    @Column(name = "end")
    @ApiModelProperty(value = "目的地")
    private String end;

    @Column(name = "work_reason")
    @ApiModelProperty(value = "工作事由")
    private String workReason;

    @Column(name = "diesel_charge")
    @ApiModelProperty(value = "柴油费")
    private Float dieselCharge;

    @Column(name = "gasoline")
    @ApiModelProperty(value = "汽油费")
    private Float gasoline;

    @Column(name = "road_bridge")
    @ApiModelProperty(value = "路桥费")
    private Float roadBridge;

    @Column(name = "park")
    @ApiModelProperty(value = "停车费")
    private Float park;

    @Column(name = "car_wash")
    @ApiModelProperty(value = "洗车费")
    private Float carWash;

    @Column(name = "car_maint")
    @ApiModelProperty(value = "车辆维修费用")
    private Float carMaint;

    @Column(name = "vehicle_tax")
    @ApiModelProperty(value = "车船税")
    private Float vehicleTax;

    @Column(name = "either")
    @ApiModelProperty(value = "其他")
    private Float either;

    @Column(name = "total")
    @ApiModelProperty(value = "总计")
    private Float total;

    @Column(name = "examine")
    @ApiModelProperty(value = "审核")
    private Integer examine;

    public void copy(Reimbursement source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}