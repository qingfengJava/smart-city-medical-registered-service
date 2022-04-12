package com.qingfeng.hosp.repository;

import com.qingfeng.model.model.hosp.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * MongoRepository  医院科室
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/12
 */
@Repository
public interface DepartmentRepository extends MongoRepository<Department,String> {

    /**
     * 查询科室信息是否存在
     * @param hoscode
     * @param depcode
     * @return
     */
    Department getDepartmentByHoscodeAndDepcode(String hoscode, String depcode);
}
