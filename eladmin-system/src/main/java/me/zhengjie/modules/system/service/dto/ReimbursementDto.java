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
public class ReimbursementDto implements Serializable {

    /** 报销单ID */
    private String id;

    /** 报销部门 */
    private String depart;

    /** 报销日期 */
    private Timestamp date;

    /** 车牌号 */
    private String license;

    /** 驾驶员 */
    private String driver;

    /** 呈报人 */
    private String informant;

    /** 乘车日期 */
    private Timestamp rideDate;

    /** 起始地址 */
    private String begin;

    /** 目的地 */
    private String end;

    /** 工作事由 */
    private String workReason;

    /** 柴油费 */
    private Float dieselCharge;

    /** 汽油费 */
    private Float gasoline;

    /** 路桥费 */
    private Float roadBridge;

    /** 停车费 */
    private Float park;

    /** 洗车费 */
    private Float carWash;

    /** 车辆维修费用 */
    private Float carMaint;

    /** 车船税 */
    private Float vehicleTax;

    /** 其他 */
    private Float either;

    /** 总计 */
    private Float total;

    /** 审核 */
    private Integer examine;
}