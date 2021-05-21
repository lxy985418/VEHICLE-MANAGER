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
import me.zhengjie.modules.system.domain.Driverinfo;
import me.zhengjie.modules.system.service.DriverinfoService;
import me.zhengjie.modules.system.service.dto.DriverinfoQueryCriteria;
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
* @date 2021-05-02
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "驾驶员信息管理")
@RequestMapping("/api/driverinfo")
public class DriverinfoController {

    private final DriverinfoService driverinfoService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('driverinfo:list')")
    public void download(HttpServletResponse response, DriverinfoQueryCriteria criteria) throws IOException {
        driverinfoService.download(driverinfoService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询驾驶员信息")
    @ApiOperation("查询驾驶员信息")
    @PreAuthorize("@el.check('driverinfo:list')")
    public ResponseEntity<Object> query(DriverinfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(driverinfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增驾驶员信息")
    @ApiOperation("新增驾驶员信息")
    @PreAuthorize("@el.check('driverinfo:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Driverinfo resources){
        return new ResponseEntity<>(driverinfoService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改驾驶员信息")
    @ApiOperation("修改驾驶员信息")
    @PreAuthorize("@el.check('driverinfo:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Driverinfo resources){
        driverinfoService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除驾驶员信息")
    @ApiOperation("删除驾驶员信息")
    @PreAuthorize("@el.check('driverinfo:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        driverinfoService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}