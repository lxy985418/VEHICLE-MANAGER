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
import me.zhengjie.modules.system.domain.Fuel;
import me.zhengjie.modules.system.service.FuelService;
import me.zhengjie.modules.system.service.dto.FuelQueryCriteria;
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
* @date 2021-03-31
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "燃油报表管理")
@RequestMapping("/api/fuel")
public class FuelController {

    private final FuelService fuelService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('fuel:list')")
    public void download(HttpServletResponse response, FuelQueryCriteria criteria) throws IOException {
        fuelService.download(fuelService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询燃油报表")
    @ApiOperation("查询燃油报表")
    @PreAuthorize("@el.check('fuel:list')")
    public ResponseEntity<Object> query(FuelQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(fuelService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增燃油报表")
    @ApiOperation("新增燃油报表")
    @PreAuthorize("@el.check('fuel:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Fuel resources){
        return new ResponseEntity<>(fuelService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改燃油报表")
    @ApiOperation("修改燃油报表")
    @PreAuthorize("@el.check('fuel:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Fuel resources){
        fuelService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除燃油报表")
    @ApiOperation("删除燃油报表")
    @PreAuthorize("@el.check('fuel:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Integer[] ids) {
        fuelService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}