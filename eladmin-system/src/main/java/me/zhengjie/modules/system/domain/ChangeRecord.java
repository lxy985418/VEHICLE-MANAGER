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
@Table(name="change_record")
public class ChangeRecord implements Serializable {

    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "单号")
    private String id;

    @Column(name = "change_date")
    @ApiModelProperty(value = "变更日期")
    private Timestamp changeDate;

    @Column(name = "change_reason")
    @ApiModelProperty(value = "变更原因")
    private String changeReason;

    @Column(name = "changes")
    @ApiModelProperty(value = "变更事项")
    private String changes;

    @Column(name = "registrant")
    @ApiModelProperty(value = "登记人")
    private String registrant;

    @Column(name = "before")
    @ApiModelProperty(value = "变更前车牌")
    private String before;

    @Column(name = "after")
    @ApiModelProperty(value = "变更后车牌")
    private String after;

    public void copy(ChangeRecord source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}