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
import me.zhengjie.modules.system.domain.Vehicle;
import me.zhengjie.modules.system.service.VehicleService;
import me.zhengjie.modules.system.service.dto.VehicleQueryCriteria;
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
* @date 2021-03-29
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "车辆信息管理")
@RequestMapping("/api/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('vehicle:list')")
    public void download(HttpServletResponse response, VehicleQueryCriteria criteria) throws IOException {
        vehicleService.download(vehicleService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询车辆信息")
    @ApiOperation("查询车辆信息")
    @PreAuthorize("@el.check('vehicle:list')")
    public ResponseEntity<Object> query(VehicleQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(vehicleService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增车辆信息")
    @ApiOperation("新增车辆信息")
    @PreAuthorize("@el.check('vehicle:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Vehicle resources){
        return new ResponseEntity<>(vehicleService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改车辆信息")
    @ApiOperation("修改车辆信息")
    @PreAuthorize("@el.check('vehicle:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Vehicle resources){
        vehicleService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除车辆信息")
    @ApiOperation("删除车辆信息")
    @PreAuthorize("@el.check('vehicle:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        vehicleService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}