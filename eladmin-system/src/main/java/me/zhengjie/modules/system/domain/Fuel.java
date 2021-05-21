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
* @date 2021-03-31
**/
@Entity
@Data
@Table(name="fuel")
public class Fuel implements Serializable {

    @Column(name = "num")
    @ApiModelProperty(value = "序号")
    private Integer num;

    @Column(name = "date")
    @ApiModelProperty(value = "充值日期")
    private Timestamp date;

    @Column(name = "cart_id")
    @ApiModelProperty(value = "银行卡号")
    private Integer cartId;

    @Column(name = "lic")
    @ApiModelProperty(value = "车牌号")
    private String lic;

    @Column(name = "money")
    @ApiModelProperty(value = "充值金额")
    private String money;

    @Column(name = "remarks")
    @ApiModelProperty(value = "备注")
    private String remarks;

    @Id
    @Column(name = "odd_num")
    @ApiModelProperty(value = "充值单号")
    private Integer oddNum;

    public void copy(Fuel source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}