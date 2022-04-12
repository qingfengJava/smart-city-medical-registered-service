package com.qingfeng.hosp.service;

import com.qingfeng.model.model.hosp.Schedule;
import com.qingfeng.model.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * 医院排班业务层接口
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2022/4/12
 */
public interface ScheduleService {

    /**
     * 上传医院排班
     * @param paramMap
     */
    void save(Map<String, Object> paramMap);

    /**
     * 查询医院排班
     * @param page
     * @param limit
     * @param scheduleQueryVo
     * @return
     */
    Page<Schedule> findPageSchedule(int page, int limit, ScheduleQueryVo scheduleQueryVo);

    /**
     * 根据医院编号和排班编号删除医院排班
     * @param hoscode
     * @param hosSchecodeId
     */
    void remove(String hoscode, String hosSchecodeId);
}
