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
import me.zhengjie.modules.system.domain.Apply;
import me.zhengjie.modules.system.service.ApplyService;
import me.zhengjie.modules.system.service.dto.ApplyQueryCriteria;
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
@Api(tags = "调度：用车申请管理")
@RequestMapping("/api/apply")
public class ApplyController {

    private final ApplyService applyService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('apply:list')")
    public void download(HttpServletResponse response, ApplyQueryCriteria criteria) throws IOException {
        applyService.download(applyService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询调度：用车申请")
    @ApiOperation("查询调度：用车申请")
    @PreAuthorize("@el.check('apply:list')")
    public ResponseEntity<Object> query(ApplyQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(applyService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增调度：用车申请")
    @ApiOperation("新增调度：用车申请")
    @PreAuthorize("@el.check('apply:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Apply resources){
        return new ResponseEntity<>(applyService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改调度：用车申请")
    @ApiOperation("修改调度：用车申请")
    @PreAuthorize("@el.check('apply:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Apply resources){
        applyService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除调度：用车申请")
    @ApiOperation("删除调度：用车申请")
    @PreAuthorize("@el.check('apply:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        applyService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}