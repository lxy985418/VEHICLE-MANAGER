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
import me.zhengjie.modules.system.domain.CarCollect;
import me.zhengjie.modules.system.service.CarCollectService;
import me.zhengjie.modules.system.service.dto.CarCollectQueryCriteria;
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
@Api(tags = "收车记录管理")
@RequestMapping("/api/carCollect")
public class CarCollectController {

    private final CarCollectService carCollectService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('carCollect:list')")
    public void download(HttpServletResponse response, CarCollectQueryCriteria criteria) throws IOException {
        carCollectService.download(carCollectService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询收车记录")
    @ApiOperation("查询收车记录")
    @PreAuthorize("@el.check('carCollect:list')")
    public ResponseEntity<Object> query(CarCollectQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(carCollectService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增收车记录")
    @ApiOperation("新增收车记录")
    @PreAuthorize("@el.check('carCollect:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody CarCollect resources){
        return new ResponseEntity<>(carCollectService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改收车记录")
    @ApiOperation("修改收车记录")
    @PreAuthorize("@el.check('carCollect:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody CarCollect resources){
        carCollectService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除收车记录")
    @ApiOperation("删除收车记录")
    @PreAuthorize("@el.check('carCollect:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String[] ids) {
        carCollectService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}