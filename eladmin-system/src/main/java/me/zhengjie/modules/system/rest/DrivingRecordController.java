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
import me.zhengjie.modules.system.domain.DrivingRecord;
import me.zhengjie.modules.system.service.DrivingRecordService;
import me.zhengjie.modules.system.service.dto.DrivingRecordQueryCriteria;
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
@Api(tags = "单位出车记录管理")
@RequestMapping("/api/drivingRecord")
public class DrivingRecordController {

    private final DrivingRecordService drivingRecordService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('drivingRecord:list')")
    public void download(HttpServletResponse response, DrivingRecordQueryCriteria criteria) throws IOException {
        drivingRecordService.download(drivingRecordService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询单位出车记录")
    @ApiOperation("查询单位出车记录")
    @PreAuthorize("@el.check('drivingRecord:list')")
    public ResponseEntity<Object> query(DrivingRecordQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(drivingRecordService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增单位出车记录")
    @ApiOperation("新增单位出车记录")
    @PreAuthorize("@el.check('drivingRecord:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody DrivingRecord resources){
        return new ResponseEntity<>(drivingRecordService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改单位出车记录")
    @ApiOperation("修改单位出车记录")
    @PreAuthorize("@el.check('drivingRecord:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody DrivingRecord resources){
        drivingRecordService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除单位出车记录")
    @ApiOperation("删除单位出车记录")
    @PreAuthorize("@el.check('drivingRecord:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        drivingRecordService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}