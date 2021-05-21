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
@Table(name="maint_record")
public class MaintRecord implements Serializable {

    @Column(name = "registration_date")
    @ApiModelProperty(value = "登记日期")
    private Timestamp registrationDate;

    @Column(name = "license")
    @ApiModelProperty(value = "车牌")
    private String license;

    @Column(name = "maintenance")
    @ApiModelProperty(value = "维修类别")
    private String maintenance;

    @Column(name = "designation")
    @ApiModelProperty(value = "名称")
    private String designation;

    @Column(name = "specification")
    @ApiModelProperty(value = "规格")
    private String specification;

    @Column(name = "num")
    @ApiModelProperty(value = "数量")
    private Integer num;

    @Column(name = "price")
    @ApiModelProperty(value = "价格")
    private Float price;

    @Column(name = "manufacturer")
    @ApiModelProperty(value = "厂家")
    private String manufacturer;

    @Column(name = "registrant")
    @ApiModelProperty(value = "登记人")
    private String registrant;

    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "id")
    private String id;

    public void copy(MaintRecord source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}