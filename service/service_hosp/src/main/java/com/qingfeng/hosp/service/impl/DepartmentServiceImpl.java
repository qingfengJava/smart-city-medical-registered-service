package com.qingfeng.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qingfeng.hosp.repository.DepartmentRepository;
import com.qingfeng.hosp.service.DepartmentService;
import com.qingfeng.model.model.hosp.Department;
import com.qingfeng.model.vo.hosp.DepartmentQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * 医院科室的业务逻辑实现类
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/12
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * 添加医院科室信息
     * @param paramMap
     */
    @Override
    public void save(Map<String, Object> paramMap) {
        String paramMapString = JSONObject.toJSONString(paramMap);
        Department department = JSONObject.parseObject(paramMapString, Department.class);

        //根据医院编号 和 科室编号查询
        Department departmentExist = departmentRepository.getDepartmentByHoscodeAndDepcode(department.getHoscode(), department.getDepcode());
        //判断是否已经存在该科室
        if (departmentExist != null) {
            departmentExist.setUpdateTime(new Date());
            departmentExist.setIsDeleted(0);
            departmentRepository.save(departmentExist);
        }else {
            department.setCreateTime(new Date());
            department.setUpdateTime(new Date());
            department.setIsDeleted(0);
            departmentRepository.save(department);
        }
    }

    /**
     * 分页查询医院科室信息
     * @param page
     * @param limit
     * @param departmentQueryVo
     * @return
     */
    @Override
    public Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo) {
        //创建Pageable对象，设置当前页和每页记录数‘
        //0是第一页
        Pageable pageable = PageRequest.of(page - 1, limit);
        //创建Example对象封装查询条件
        Department department = new Department();
        BeanUtils.copyProperties(departmentQueryVo, department);
        department.setIsDeleted(0);

        ExampleMatcher matcher = ExampleMatcher.matching()
                //模糊查询
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                //忽略大小写
                .withIgnoreCase(true);
        Example<Department> example = Example.of(department, matcher);

        Page<Department> all = departmentRepository.findAll(example, pageable);
        return all;
    }

    /**
     * 根据医院编号和科室编号删除医院科室信息
     * @param hoscode
     * @param depcode
     */
    @Override
    public void remove(String hoscode, String depcode) {
        //根据医院编号和科室编号查询科室信息
        Department department = departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
        //判断是否存在该科室
        if (department != null) {
            //调用删除的方法
            departmentRepository.deleteById(department.getId());
        }
    }
}
