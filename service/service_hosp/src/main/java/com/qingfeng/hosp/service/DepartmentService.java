package com.qingfeng.hosp.service;

import com.qingfeng.model.model.hosp.Department;
import com.qingfeng.model.vo.hosp.DepartmentQueryVo;
import com.qingfeng.model.vo.hosp.DepartmentVo;
import org.springframework.data.domain.Page;

import java.util.List;
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
     * @param hoscode  医院编号
     * @param depcode  科室编号
     */
    void remove(String hoscode, String depcode);

    /**
     * 根据医院编号查询科室信息
     * @param hoscode
     * @return
     */
    List<DepartmentVo> findDeptTree(String hoscode);

    /**
     * 根据科室编号 和 医院编号， 查询科室名称
     * @param hoscode
     * @param depcode
     * @return
     */
    String getDepName(String hoscode, String depcode);

    /**
     * 根据科室编号 和医院编号，查询科室
     * @param hoscode
     * @param depcode
     * @return
     */
    Department getDepartment(String hoscode, String depcode);
}
