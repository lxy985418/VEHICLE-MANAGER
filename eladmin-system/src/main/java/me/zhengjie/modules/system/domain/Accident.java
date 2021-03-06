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
* @date 2021-04-01
**/
@Entity
@Data
@Table(name="accident")
public class Accident implements Serializable {

    @Id
    @Column(name = "accident_id")
    @ApiModelProperty(value = "事故单号")
    private String accidentId;

    @Column(name = "accident_DATE")
    @ApiModelProperty(value = "事故日期")
    private Timestamp accidentDate;

    @Column(name = "accident_place")
    @ApiModelProperty(value = "事故地点")
    private String accidentPlace;

    @Column(name = "accident_nature")
    @ApiModelProperty(value = "事故性质")
    private String accidentNature;

    @Column(name = "accident_kind")
    @ApiModelProperty(value = "事故类别")
    private String accidentKind;

    @Column(name = "economy_loss")
    @ApiModelProperty(value = "直接经济损失")
    private Float economyLoss;

    @Column(name = "registrant")
    @ApiModelProperty(value = "登记人")
    private String registrant;

    @Column(name = "accident_loss")
    @ApiModelProperty(value = "事故损失")
    private String accidentLoss;

    public void copy(Accident source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}