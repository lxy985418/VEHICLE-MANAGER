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
@Table(name="driving_record")
public class DrivingRecord implements Serializable {

    @Id
    @Column(name = "driving_id")
    @ApiModelProperty(value = "出车单号")
    private String drivingId;

    @Column(name = "driving_diver",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "出车司机")
    private String drivingDiver;

    @Column(name = "driving_reason")
    @ApiModelProperty(value = "出车事由")
    private String drivingReason;

    @Column(name = "driving_date",nullable = false)
    @NotNull
    @ApiModelProperty(value = "出车时间")
    private Timestamp drivingDate;

    @Column(name = "driving_end_date")
    @ApiModelProperty(value = "预计归车时间")
    private Timestamp drivingEndDate;

    @Column(name = "driving_remarks")
    @ApiModelProperty(value = "备注")
    private String drivingRemarks;

    @Column(name = "driving_license")
    @ApiModelProperty(value = "车牌")
    private String drivingLicense;

    @Column(name = "driving_begging")
    @ApiModelProperty(value = "出车地点")
    private String drivingBegging;

    public void copy(DrivingRecord source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}