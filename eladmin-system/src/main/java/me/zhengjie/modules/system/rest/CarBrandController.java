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

import com.alibaba.fastjson.JSONObject;
import me.zhengjie.annotation.Log;
import me.zhengjie.modules.system.domain.CarBrand;
import me.zhengjie.modules.system.service.CarBrandService;
import me.zhengjie.modules.system.service.dto.CarBrandQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.xml.transform.Source;

/**
* @website https://el-admin.vip
* @author Lee
* @date 2021-04-01
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "品牌清单管理")
@RequestMapping("/api/carBrand")
public class CarBrandController {

    private final CarBrandService carBrandService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('carBrand:list')")
    public void download(HttpServletResponse response, CarBrandQueryCriteria criteria) throws IOException {
        carBrandService.download(carBrandService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询品牌清单")
    @ApiOperation("查询品牌清单")
    @PreAuthorize("@el.check('carBrand:list')")
    public ResponseEntity<Object> query(CarBrandQueryCriteria criteria, Pageable pageable){
        Map<String, Object> stringObjectMap = carBrandService.queryAll(criteria, pageable);

        return new ResponseEntity<>(stringObjectMap,HttpStatus.OK);
    }

    @PostMapping
    @Log("新增品牌清单")
    @ApiOperation("新增品牌清单")
    @PreAuthorize("@el.check('carBrand:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody CarBrand resources){
        return new ResponseEntity<>(carBrandService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改品牌清单")
    @ApiOperation("修改品牌清单")
    @PreAuthorize("@el.check('carBrand:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody CarBrand resources){
        carBrandService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除品牌清单")
    @ApiOperation("删除品牌清单")
    @PreAuthorize("@el.check('carBrand:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Integer[] ids) {
        carBrandService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}