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
package me.zhengjie.modules.system.service;

import me.zhengjie.modules.system.domain.Dispatching;
import me.zhengjie.modules.system.service.dto.DispatchingDto;
import me.zhengjie.modules.system.service.dto.DispatchingQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @website https://el-admin.vip
* @description 服务接口
* @author Lee
* @date 2021-04-07
**/
public interface DispatchingService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(DispatchingQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<DispatchingDto>
    */
    List<DispatchingDto> queryAll(DispatchingQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param dispatchingId ID
     * @return DispatchingDto
     */
    DispatchingDto findById(Long dispatchingId);

    /**
    * 创建
    * @param resources /
    * @return DispatchingDto
    */
    DispatchingDto create(Dispatching resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(Dispatching resources);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Long[] ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<DispatchingDto> all, HttpServletResponse response) throws IOException;
}