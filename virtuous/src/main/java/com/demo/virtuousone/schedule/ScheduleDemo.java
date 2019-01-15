package com.demo.virtuousone.schedule;

import com.demo.virtuousone.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: 吴宸煊
 * Date: Created in  2019/1/13/0013
 * Description: 定时任务使用
 */
@Component
public class ScheduleDemo {
    private static Logger logger = LoggerFactory.getLogger(ScheduleDemo.class);

    public final static long ONE_Minute = 10 * 100000;

    //@Scheduled(fixedRate = 10000)这个表示服务在启动的时候会先执行一次,然后再每隔10秒再调用一次
    //@Scheduled(initialDelay = 10000,fixedRate = 15000)这个表示在容器启动后,延迟10秒后再执行一次定时器,以后每15秒再执行一次该定时器
    //@Scheduled(fixedDelay=ONE_Minute) 每隔多久执行一次
    @Scheduled(fixedDelay = ONE_Minute)
    public void fixedDelayJob() {
        logger.info(CommonUtil.getTime() + (" >>fixedDelay执行...."));
    }

    //0 0 3 * * ?     每天3点执行
    //0 5 3 * * ?     每天3点5分执行
    //0 5 3 ? * *     每天3点5分执行，与上面作用相同
    //0 5/10 3 * * ?  每天3点的 5分，15分，25分，35分，45分，55分这几个时间点执行
    //0 10 3 ? * 1    每周星期天，3点10分 执行，注：1表示星期天
    //0 10 3 ? * 1#3  每个月的第三个星期，星期天 执行，#号只能出现在星期的位置
   /* @Scheduled(cron="0 48 15 * * ?")
    public void cronJob(){
        logger.info(CommonUtil.getTime()+(" >>cron执行...."));
    }*/
}
