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
import me.zhengjie.modules.system.domain.Reimbursement;
import me.zhengjie.modules.system.service.ReimbursementService;
import me.zhengjie.modules.system.service.dto.ReimbursementQueryCriteria;
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
@Api(tags = "报销单管理")
@RequestMapping("/api/reimbursement")
public class ReimbursementController {

    private final ReimbursementService reimbursementService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
        @PreAuthorize("@el.check('reimbursement:list')")
    public void download(HttpServletResponse response, ReimbursementQueryCriteria criteria) throws IOException {
        reimbursementService.download(reimbursementService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询报销单")
    @ApiOperation("查询报销单")
    @PreAuthorize("@el.check('reimbursement:list')")
    public ResponseEntity<Object> query(ReimbursementQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(reimbursementService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增报销单")
    @ApiOperation("新增报销单")
    @PreAuthorize("@el.check('reimbursement:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Reimbursement resources){
        return new ResponseEntity<>(reimbursementService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改报销单")
    @ApiOperation("修改报销单")
    @PreAuthorize("@el.check('reimbursement:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Reimbursement resources){
        reimbursementService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除报销单")
    @ApiOperation("删除报销单")
    @PreAuthorize("@el.check('reimbursement:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        reimbursementService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}