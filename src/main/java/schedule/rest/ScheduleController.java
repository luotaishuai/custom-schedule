package schedule.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import schedule.common.RestResp;
import schedule.entity.ScheduleData;
import schedule.service.ScheduleService;

import javax.annotation.Resource;


/**
 * @author anonymity
 * @create 2018-09-30 10:04
 **/
@RestController
@RequestMapping(value = "/schedule")
public class ScheduleController {
    @Resource
    private ScheduleService scheduleService;

    @PostMapping(value = "/start")
    public RestResp startJob(@RequestBody ScheduleData data){
        return scheduleService.startJob(data);
    }
    @PostMapping(value = "/stop")
    public RestResp stopJob(@RequestBody ScheduleData data){
        return scheduleService.stopJob(data);
    }
}
