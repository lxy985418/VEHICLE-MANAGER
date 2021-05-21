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
import me.zhengjie.modules.system.domain.VehicleInfo;
import me.zhengjie.modules.system.service.VehicleInfoService;
import me.zhengjie.modules.system.service.dto.VehicleInfoQueryCriteria;
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
* @date 2021-04-05
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "单位车辆档案管理")
@RequestMapping("/api/vehicleInfo")
public class VehicleInfoController {

    private final VehicleInfoService vehicleInfoService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('vehicleInfo:list')")
    public void download(HttpServletResponse response, VehicleInfoQueryCriteria criteria) throws IOException {
        vehicleInfoService.download(vehicleInfoService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询单位车辆档案")
    @ApiOperation("查询单位车辆档案")
    @PreAuthorize("@el.check('vehicleInfo:list')")
    public ResponseEntity<Object> query(VehicleInfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(vehicleInfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增单位车辆档案")
    @ApiOperation("新增单位车辆档案")
    @PreAuthorize("@el.check('vehicleInfo:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody VehicleInfo resources){
        return new ResponseEntity<>(vehicleInfoService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改单位车辆档案")
    @ApiOperation("修改单位车辆档案")
    @PreAuthorize("@el.check('vehicleInfo:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody VehicleInfo resources){
        vehicleInfoService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除单位车辆档案")
    @ApiOperation("删除单位车辆档案")
    @PreAuthorize("@el.check('vehicleInfo:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        vehicleInfoService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}