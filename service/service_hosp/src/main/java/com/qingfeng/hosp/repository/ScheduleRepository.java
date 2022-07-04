package com.qingfeng.hosp.repository;

import com.qingfeng.model.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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

    /**
     * 根据医院编号、科室编号和工作日期，查询排班详细信息
     * @param hoscode
     * @param depcode
     * @param toDate
     * @return
     */
    List<Schedule> findScheduleByHoscodeAndDepcodeAndWorkDate(String hoscode, String depcode, Date toDate);
}
