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
package me.zhengjie.modules.system.service.impl;

import me.zhengjie.modules.system.domain.Apply;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.repository.ApplyRepository;
import me.zhengjie.modules.system.service.ApplyService;
import me.zhengjie.modules.system.service.dto.ApplyDto;
import me.zhengjie.modules.system.service.dto.ApplyQueryCriteria;
import me.zhengjie.modules.system.service.mapstruct.ApplyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @website https://el-admin.vip
* @description 服务实现
* @author Lee
* @date 2021-04-02
**/
@Service
@RequiredArgsConstructor
public class ApplyServiceImpl implements ApplyService {

    private final ApplyRepository applyRepository;
    private final ApplyMapper applyMapper;

    @Override
    public Map<String,Object> queryAll(ApplyQueryCriteria criteria, Pageable pageable){
        Page<Apply> page = applyRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(applyMapper::toDto));
    }

    @Override
    public List<ApplyDto> queryAll(ApplyQueryCriteria criteria){
        return applyMapper.toDto(applyRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ApplyDto findById(String applyId) {
        Apply apply = applyRepository.findById(applyId).orElseGet(Apply::new);
        ValidationUtil.isNull(apply.getApplyId(),"Apply","applyId",applyId);
        return applyMapper.toDto(apply);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApplyDto create(Apply resources) {
        resources.setApplyId(IdUtil.simpleUUID()); 
        return applyMapper.toDto(applyRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Apply resources) {
        Apply apply = applyRepository.findById(resources.getApplyId()).orElseGet(Apply::new);
        ValidationUtil.isNull( apply.getApplyId(),"Apply","id",resources.getApplyId());
        apply.copy(resources);
        applyRepository.save(apply);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String applyId : ids) {
            applyRepository.deleteById(applyId);
        }
    }

    @Override
    public void download(List<ApplyDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ApplyDto apply : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("申请人", apply.getApplyPerson());
            map.put("申请日期", apply.getApplyDate());
            map.put("乘客", apply.getApplyPassenger());
            map.put("乘车开始时间", apply.getApplyBeggingDate());
            map.put("乘车结束时间", apply.getApplyEnddingDate());
            map.put("乘车起始地点", apply.getApplyBeggingPlace());
            map.put("乘车结束地点", apply.getApplyEnddingPlace());
            map.put("审核状态", apply.getApplyState());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}