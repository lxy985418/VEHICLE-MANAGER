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
* @date 2021-05-02
**/
@Entity
@Data
@Table(name="driverinfo")
public class Driverinfo implements Serializable {

    @Id
    @Column(name = "Driver_Id")
    @ApiModelProperty(value = "序号")
    private String driverId;

    @Column(name = "Name",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "驾驶员名称")
    private String name;

    @Column(name = "Lic_type_num")
    @ApiModelProperty(value = "驾驶证类型")
    private String licTypeNum;

    @Column(name = "Sex")
    @ApiModelProperty(value = "性别")
    private String sex;

    @Column(name = "Birth")
    @ApiModelProperty(value = "生日")
    private Timestamp birth;

    @Column(name = "Address")
    @ApiModelProperty(value = "地址")
    private String address;

    @Column(name = "Opening_date",nullable = false)
    @NotNull
    @ApiModelProperty(value = "发证日期")
    private Timestamp openingDate;

    @Column(name = "Next_examine")
    @ApiModelProperty(value = "下次年检日期")
    private Timestamp nextExamine;

    @Column(name = "Surplus_score")
    @ApiModelProperty(value = "剩余分值")
    private String surplusScore;

    @Column(name = "Lic_statusnum")
    @ApiModelProperty(value = "驾驶证状态编号")
    private Integer licStatusnum;

    public void copy(Driverinfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}