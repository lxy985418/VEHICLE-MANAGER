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
@Table(name="car_collect")
public class CarCollect implements Serializable {

    @Id
    @Column(name = "car_collect_id")
    @ApiModelProperty(value = "收车记录序号")
    private String carCollectId;

    @Column(name = "driving_id",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "出行单号")
    private String drivingId;

    @Column(name = "car_collect_begging")
    @ApiModelProperty(value = "出车地点")
    private String carCollectBegging;

    @Column(name = "car_collect_endding")
    @ApiModelProperty(value = "收车地点")
    private String carCollectEndding;

    @Column(name = "car_collect_begindate")
    @ApiModelProperty(value = "出车时间")
    private Timestamp carCollectBegindate;

    @Column(name = "car_collect_enddate")
    @ApiModelProperty(value = "收车时间")
    private Timestamp carCollectEnddate;

    @Column(name = "car_collect_distance")
    @ApiModelProperty(value = "出行距离")
    private Double carCollectDistance;

    @Column(name = "car_collect_driver")
    @ApiModelProperty(value = "司机")
    private String carCollectDriver;

    @Column(name = "car_collect_remark")
    @ApiModelProperty(value = "备注")
    private String carCollectRemark;

    public void copy(CarCollect source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}