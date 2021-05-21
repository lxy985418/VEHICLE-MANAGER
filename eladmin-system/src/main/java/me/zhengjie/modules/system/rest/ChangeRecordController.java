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
import me.zhengjie.modules.system.domain.ChangeRecord;
import me.zhengjie.modules.system.service.ChangeRecordService;
import me.zhengjie.modules.system.service.dto.ChangeRecordQueryCriteria;
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
@Api(tags = "车辆变更管理")
@RequestMapping("/api/changeRecord")
public class ChangeRecordController {

    private final ChangeRecordService changeRecordService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('changeRecord:list')")
    public void download(HttpServletResponse response, ChangeRecordQueryCriteria criteria) throws IOException {
        changeRecordService.download(changeRecordService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询车辆变更")
    @ApiOperation("查询车辆变更")
    @PreAuthorize("@el.check('changeRecord:list')")
    public ResponseEntity<Object> query(ChangeRecordQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(changeRecordService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增车辆变更")
    @ApiOperation("新增车辆变更")
    @PreAuthorize("@el.check('changeRecord:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ChangeRecord resources){
        return new ResponseEntity<>(changeRecordService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改车辆变更")
    @ApiOperation("修改车辆变更")
    @PreAuthorize("@el.check('changeRecord:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ChangeRecord resources){
        changeRecordService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除车辆变更")
    @ApiOperation("删除车辆变更")
    @PreAuthorize("@el.check('changeRecord:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        changeRecordService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}