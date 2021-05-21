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
* @date 2021-04-07
**/
@Data
public class DrivingRecordDto implements Serializable {

    /** 出车单号 */
    private String drivingId;

    /** 出车司机 */
    private String drivingDiver;

    /** 出车事由 */
    private String drivingReason;

    /** 出车时间 */
    private Timestamp drivingDate;

    /** 预计归车时间 */
    private Timestamp drivingEndDate;

    /** 备注 */
    private String drivingRemarks;

    /** 车牌 */
    private String drivingLicense;

    /** 出车地点 */
    private String drivingBegging;
}