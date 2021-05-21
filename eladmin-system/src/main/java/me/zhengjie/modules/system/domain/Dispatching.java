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
* @date 2021-04-07
**/
@Entity
@Data
@Table(name="dispatching")
public class Dispatching implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dispatching_id")
    @ApiModelProperty(value = "调度单号")
    private Long dispatchingId;

    @Column(name = "dispatching_apply_id",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "用车申请单号")
    private String dispatchingApplyId;

    @Column(name = "dispatching_license")
    @ApiModelProperty(value = "用车车牌")
    private String dispatchingLicense;

    @Column(name = "dispatching_driver")
    @ApiModelProperty(value = "用车司机")
    private String dispatchingDriver;

    @Column(name = "dispatching_begging")
    @ApiModelProperty(value = "申请出发地点")
    private String dispatchingBegging;

    @Column(name = "dispatching_ending")
    @ApiModelProperty(value = "申请结束地点")
    private String dispatchingEnding;

    @Column(name = "dispatching_date")
    @ApiModelProperty(value = "调度时间")
    private Timestamp dispatchingDate;

    @Column(name = "dispatching_approved")
    @ApiModelProperty(value = "审批人")
    private String dispatchingApproved;

    public void copy(Dispatching source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}