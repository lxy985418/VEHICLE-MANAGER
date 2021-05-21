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
@Table(name="apply")
public class Apply implements Serializable {

    @Id
    @Column(name = "apply_id")
    @ApiModelProperty(value = "applyId")
    private String applyId;

    @Column(name = "apply_person")
    @ApiModelProperty(value = "申请人")
    private String applyPerson;

    @Column(name = "apply_date")
    @ApiModelProperty(value = "申请日期")
    private Timestamp applyDate;

    @Column(name = "apply_passenger")
    @ApiModelProperty(value = "乘客")
    private String applyPassenger;

    @Column(name = "apply_begging_date")
    @ApiModelProperty(value = "乘车开始时间")
    private Timestamp applyBeggingDate;

    @Column(name = "apply_endding_date")
    @ApiModelProperty(value = "乘车结束时间")
    private Timestamp applyEnddingDate;

    @Column(name = "apply_begging_place")
    @ApiModelProperty(value = "乘车起始地点")
    private String applyBeggingPlace;

    @Column(name = "apply_endding_place")
    @ApiModelProperty(value = "乘车结束地点")
    private String applyEnddingPlace;

    @Column(name = "apply_state")
    @ApiModelProperty(value = "审核状态")
    private Integer applyState;

    public void copy(Apply source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}