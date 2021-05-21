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
package me.zhengjie.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author Lee
* @date 2021-04-02
**/
@Data
public class MaintRecordDto implements Serializable {

    /** 登记日期 */
    private Timestamp registrationDate;

    /** 车牌 */
    private String license;

    /** 维修类别 */
    private String maintenance;

    /** 名称 */
    private String designation;

    /** 规格 */
    private String specification;

    /** 数量 */
    private Integer num;

    /** 价格 */
    private Float price;

    /** 厂家 */
    private String manufacturer;

    /** 登记人 */
    private String registrant;

    private String id;
}