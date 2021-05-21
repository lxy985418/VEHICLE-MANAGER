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

import me.zhengjie.modules.system.domain.Dispatching;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.repository.DispatchingRepository;
import me.zhengjie.modules.system.service.DispatchingService;
import me.zhengjie.modules.system.service.dto.DispatchingDto;
import me.zhengjie.modules.system.service.dto.DispatchingQueryCriteria;
import me.zhengjie.modules.system.service.mapstruct.DispatchingMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
* @date 2021-04-07
**/
@Service
@RequiredArgsConstructor
public class DispatchingServiceImpl implements DispatchingService {

    private final DispatchingRepository dispatchingRepository;
    private final DispatchingMapper dispatchingMapper;

    @Override
    public Map<String,Object> queryAll(DispatchingQueryCriteria criteria, Pageable pageable){
        Page<Dispatching> page = dispatchingRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(dispatchingMapper::toDto));
    }

    @Override
    public List<DispatchingDto> queryAll(DispatchingQueryCriteria criteria){
        return dispatchingMapper.toDto(dispatchingRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public DispatchingDto findById(Long dispatchingId) {
        Dispatching dispatching = dispatchingRepository.findById(dispatchingId).orElseGet(Dispatching::new);
        ValidationUtil.isNull(dispatching.getDispatchingId(),"Dispatching","dispatchingId",dispatchingId);
        return dispatchingMapper.toDto(dispatching);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DispatchingDto create(Dispatching resources) {
        return dispatchingMapper.toDto(dispatchingRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Dispatching resources) {
        Dispatching dispatching = dispatchingRepository.findById(resources.getDispatchingId()).orElseGet(Dispatching::new);
        ValidationUtil.isNull( dispatching.getDispatchingId(),"Dispatching","id",resources.getDispatchingId());
        dispatching.copy(resources);
        dispatchingRepository.save(dispatching);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long dispatchingId : ids) {
            dispatchingRepository.deleteById(dispatchingId);
        }
    }

    @Override
    public void download(List<DispatchingDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (DispatchingDto dispatching : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("用车申请单号", dispatching.getDispatchingApplyId());
            map.put("用车车牌", dispatching.getDispatchingLicense());
            map.put("用车司机", dispatching.getDispatchingDriver());
            map.put("申请出发地点", dispatching.getDispatchingBegging());
            map.put("申请结束地点", dispatching.getDispatchingEnding());
            map.put("调度时间", dispatching.getDispatchingDate());
            map.put("审批人", dispatching.getDispatchingApproved());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}