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
public class DispatchingDto implements Serializable {

    /** 调度单号 */
    private Long dispatchingId;

    /** 用车申请单号 */
    private String dispatchingApplyId;

    /** 用车车牌 */
    private String dispatchingLicense;

    /** 用车司机 */
    private String dispatchingDriver;

    /** 申请出发地点 */
    private String dispatchingBegging;

    /** 申请结束地点 */
    private String dispatchingEnding;

    /** 调度时间 */
    private Timestamp dispatchingDate;

    /** 审批人 */
    private String dispatchingApproved;
}