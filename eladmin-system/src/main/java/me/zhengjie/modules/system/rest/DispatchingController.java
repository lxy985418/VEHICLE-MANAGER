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
package me.zhengjie.modules.system.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.system.domain.Dispatching;
import me.zhengjie.modules.system.service.DispatchingService;
import me.zhengjie.modules.system.service.dto.DispatchingQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @website https://el-admin.vip
* @author Lee
* @date 2021-04-07
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "用车调度管理")
@RequestMapping("/api/dispatching")
public class DispatchingController {

    private final DispatchingService dispatchingService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('dispatching:list')")
    public void download(HttpServletResponse response, DispatchingQueryCriteria criteria) throws IOException {
        dispatchingService.download(dispatchingService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询用车调度")
    @ApiOperation("查询用车调度")
    @PreAuthorize("@el.check('dispatching:list')")
    public ResponseEntity<Object> query(DispatchingQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(dispatchingService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增用车调度")
    @ApiOperation("新增用车调度")
    @PreAuthorize("@el.check('dispatching:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Dispatching resources){
        return new ResponseEntity<>(dispatchingService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改用车调度")
    @ApiOperation("修改用车调度")
    @PreAuthorize("@el.check('dispatching:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Dispatching resources){
        dispatchingService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除用车调度")
    @ApiOperation("删除用车调度")
    @PreAuthorize("@el.check('dispatching:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        dispatchingService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}