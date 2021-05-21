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
import me.zhengjie.modules.system.domain.Accident;
import me.zhengjie.modules.system.service.AccidentService;
import me.zhengjie.modules.system.service.dto.AccidentQueryCriteria;
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
* @date 2021-04-01
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "车辆事故记录表管理")
@RequestMapping("/api/accident")
public class AccidentController {

    private final AccidentService accidentService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('accident:list')")
    public void download(HttpServletResponse response, AccidentQueryCriteria criteria) throws IOException {
        accidentService.download(accidentService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询车辆事故记录表")
    @ApiOperation("查询车辆事故记录表")
    @PreAuthorize("@el.check('accident:list')")
    public ResponseEntity<Object> query(AccidentQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(accidentService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增车辆事故记录表")
    @ApiOperation("新增车辆事故记录表")
    @PreAuthorize("@el.check('accident:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Accident resources){
        return new ResponseEntity<>(accidentService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改车辆事故记录表")
    @ApiOperation("修改车辆事故记录表")
    @PreAuthorize("@el.check('accident:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Accident resources){
        accidentService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除车辆事故记录表")
    @ApiOperation("删除车辆事故记录表")
    @PreAuthorize("@el.check('accident:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        accidentService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}