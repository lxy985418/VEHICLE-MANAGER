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
* @date 2021-05-02
**/
@Data
public class CarCollectDto implements Serializable {

    /** 收车记录序号 */
    private String carCollectId;

    /** 出行单号 */
    private String drivingId;

    /** 出车地点 */
    private String carCollectBegging;

    /** 收车地点 */
    private String carCollectEndding;

    /** 出车时间 */
    private Timestamp carCollectBegindate;

    /** 收车时间 */
    private Timestamp carCollectEnddate;

    /** 出行距离 */
    private Double carCollectDistance;

    /** 司机 */
    private String carCollectDriver;

    /** 备注 */
    private String carCollectRemark;
}