package com.qingfeng.hosp.repository;

import com.qingfeng.model.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 医院排班
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/12
 */
@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, String>{

    /**
     * 根据医院编号和排班编号查询排班信息
     * @param hoscode
     * @param hosScheduleId
     * @return
     */
    Schedule getScheduleByHoscodeAndHosScheduleId(String hoscode, String hosScheduleId);
}
