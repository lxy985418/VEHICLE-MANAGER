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
* @date 2021-04-01
**/
@Data
public class AccidentDto implements Serializable {

    /** 事故单号 */
    private String accidentId;

    /** 事故日期 */
    private Timestamp accidentDate;

    /** 事故地点 */
    private String accidentPlace;

    /** 事故性质 */
    private String accidentNature;

    /** 事故类别 */
    private String accidentKind;

    /** 直接经济损失 */
    private Float economyLoss;

    /** 登记人 */
    private String registrant;

    /** 事故损失 */
    private String accidentLoss;
}