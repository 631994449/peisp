package cn.edu.hziee.peisp.utils;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class TestQuartz extends QuartzJobBean{

    /**
     * 执行定时任务
     * @param jobExecutionContext
     * @throws JobExecutionException
     */

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //发送向右移动信号

        //设置延时
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //发送向左移动信号

    }
}
