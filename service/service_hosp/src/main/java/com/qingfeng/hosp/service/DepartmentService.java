package com.qingfeng.hosp.service;

import com.qingfeng.model.model.hosp.Department;
import com.qingfeng.model.vo.hosp.DepartmentQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * 医院科室的业务层接口
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/12
 */
public interface DepartmentService {

    /**
     * 添加医院科室信息
     * @param paramMap
     */
    void save(Map<String, Object> paramMap);

    /**
     * 分页查询医院科室信息
     * @param page
     * @param limit
     * @param departmentQueryVo
     * @return
     */
    Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo);

    /**
     * 根据医院编号和科室编号删除科室信息
     * @param hoscode
     * @param depcode
     */
    void remove(String hoscode, String depcode);
}
