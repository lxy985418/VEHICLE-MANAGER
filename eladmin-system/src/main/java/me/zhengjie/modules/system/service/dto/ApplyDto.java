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
public class ApplyDto implements Serializable {

    private String applyId;

    /** 申请人 */
    private String applyPerson;

    /** 申请日期 */
    private Timestamp applyDate;

    /** 乘客 */
    private String applyPassenger;

    /** 乘车开始时间 */
    private Timestamp applyBeggingDate;

    /** 乘车结束时间 */
    private Timestamp applyEnddingDate;

    /** 乘车起始地点 */
    private String applyBeggingPlace;

    /** 乘车结束地点 */
    private String applyEnddingPlace;

    /** 审核状态 */
    private Integer applyState;
}