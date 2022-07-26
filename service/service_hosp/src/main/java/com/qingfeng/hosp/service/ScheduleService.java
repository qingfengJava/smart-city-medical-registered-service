package com.qingfeng.hosp.service;

import com.qingfeng.model.model.hosp.Schedule;
import com.qingfeng.model.vo.hosp.ScheduleOrderVo;
import com.qingfeng.model.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;
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

    /**
     * 根据医院编号 和 科室编号  分页查询排班规则数据
     * @param page
     * @param limit
     * @param hoscode
     * @param depcode
     * @return
     */
    Map<String, Object> getRuleSchedule(long page, long limit, String hoscode, String depcode);

    /**
     * 根据医院编号，科室编号和工作日期，查询排班详情信息
     * @param hoscode
     * @param depcode
     * @param workDate
     * @return
     */
    List<Schedule> getDetailSchedule(String hoscode, String depcode, String workDate);

    /**
     * 获取可预约排班数据
     * @param page
     * @param limit
     * @param hoscode
     * @param depcode
     * @return
     */
    Object getBookingScheduleRule(Integer page, Integer limit, String hoscode, String depcode);

    /**
     * 根据排班id获取排班数据
     * @param scheduleId
     * @return
     */
    Schedule getById(String scheduleId);

    /**
     * 根据排班id获取预约下单数据
     * @param scheduleId
     * @return
     */
    ScheduleOrderVo getScheduleOrderVo(String scheduleId);

    /**
     * 修改排班
     * @param schedule
     */
    void update(Schedule schedule);

}
