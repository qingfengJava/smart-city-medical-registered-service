package com.qingfeng.hosp.repository;

import com.qingfeng.model.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * MongoRepository  医院信息
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/10
 */
@Repository
public interface HospitalRepository extends MongoRepository<Hospital, String> {

    /**
     * 判断医院信息是否存在
     * @param hoscode
     * @return
     */
    Hospital getHospitalByHoscode(String hoscode);
}
