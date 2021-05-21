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
import me.zhengjie.modules.system.domain.MaintRecord;
import me.zhengjie.modules.system.service.MaintRecordService;
import me.zhengjie.modules.system.service.dto.MaintRecordQueryCriteria;
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
* @date 2021-04-02
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "保养与维修记录管理")
@RequestMapping("/api/maintRecord")
public class MaintRecordController {

    private final MaintRecordService maintRecordService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('maintRecord:list')")
    public void download(HttpServletResponse response, MaintRecordQueryCriteria criteria) throws IOException {
        maintRecordService.download(maintRecordService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询保养与维修记录")
    @ApiOperation("查询保养与维修记录")
    @PreAuthorize("@el.check('maintRecord:list')")
    public ResponseEntity<Object> query(MaintRecordQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(maintRecordService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增保养与维修记录")
    @ApiOperation("新增保养与维修记录")
    @PreAuthorize("@el.check('maintRecord:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody MaintRecord resources){
        return new ResponseEntity<>(maintRecordService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改保养与维修记录")
    @ApiOperation("修改保养与维修记录")
    @PreAuthorize("@el.check('maintRecord:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody MaintRecord resources){
        maintRecordService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除保养与维修记录")
    @ApiOperation("删除保养与维修记录")
    @PreAuthorize("@el.check('maintRecord:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        maintRecordService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}