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

import me.zhengjie.modules.system.domain.Reimbursement;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.repository.ReimbursementRepository;
import me.zhengjie.modules.system.service.ReimbursementService;
import me.zhengjie.modules.system.service.dto.ReimbursementDto;
import me.zhengjie.modules.system.service.dto.ReimbursementQueryCriteria;
import me.zhengjie.modules.system.service.mapstruct.ReimbursementMapper;
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
public class ReimbursementServiceImpl implements ReimbursementService {

    private final ReimbursementRepository reimbursementRepository;
    private final ReimbursementMapper reimbursementMapper;

    @Override
    public Map<String,Object> queryAll(ReimbursementQueryCriteria criteria, Pageable pageable){
        Page<Reimbursement> page = reimbursementRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(reimbursementMapper::toDto));
    }

    @Override
    public List<ReimbursementDto> queryAll(ReimbursementQueryCriteria criteria){
        return reimbursementMapper.toDto(reimbursementRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ReimbursementDto findById(String id) {
        Reimbursement reimbursement = reimbursementRepository.findById(id).orElseGet(Reimbursement::new);
        ValidationUtil.isNull(reimbursement.getId(),"Reimbursement","id",id);
        return reimbursementMapper.toDto(reimbursement);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReimbursementDto create(Reimbursement resources) {
        resources.setId(IdUtil.simpleUUID()); 
        return reimbursementMapper.toDto(reimbursementRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Reimbursement resources) {
        Reimbursement reimbursement = reimbursementRepository.findById(resources.getId()).orElseGet(Reimbursement::new);
        ValidationUtil.isNull( reimbursement.getId(),"Reimbursement","id",resources.getId());
        reimbursement.copy(resources);
        reimbursementRepository.save(reimbursement);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String id : ids) {
            reimbursementRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<ReimbursementDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ReimbursementDto reimbursement : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("报销部门", reimbursement.getDepart());
            map.put("报销日期", reimbursement.getDate());
            map.put("车牌号", reimbursement.getLicense());
            map.put("驾驶员", reimbursement.getDriver());
            map.put("呈报人", reimbursement.getInformant());
            map.put("乘车日期", reimbursement.getRideDate());
            map.put("起始地址", reimbursement.getBegin());
            map.put("目的地", reimbursement.getEnd());
            map.put("工作事由", reimbursement.getWorkReason());
            map.put("柴油费", reimbursement.getDieselCharge());
            map.put("汽油费", reimbursement.getGasoline());
            map.put("路桥费", reimbursement.getRoadBridge());
            map.put("停车费", reimbursement.getPark());
            map.put("洗车费", reimbursement.getCarWash());
            map.put("车辆维修费用", reimbursement.getCarMaint());
            map.put("车船税", reimbursement.getVehicleTax());
            map.put("其他", reimbursement.getEither());
            map.put("总计", reimbursement.getTotal());
            map.put("审核", reimbursement.getExamine());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}