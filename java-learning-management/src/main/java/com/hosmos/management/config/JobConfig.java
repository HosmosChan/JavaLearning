package com.hosmos.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * 定时任务配置
 *
 * @author chenhuayang
 */
@Configuration
public class JobConfig {
    public static final String KEY = "applicationContextSchedulerContextKey";

    @Bean("adminQuartzScheduler")
    public SchedulerFactoryBean quartzScheduler(DataSource dataSource) {
        SchedulerFactoryBean quartzScheduler = new SchedulerFactoryBean();
        try {
            quartzScheduler.setQuartzProperties(PropertiesLoaderUtils.loadProperties(new ClassPathResource("quartz.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        quartzScheduler.setDataSource(dataSource);
        quartzScheduler.setOverwriteExistingJobs(true);
        quartzScheduler.setApplicationContextSchedulerContextKey(KEY);
        quartzScheduler.setStartupDelay(10);
        return quartzScheduler;
    }
//	@Autowired
//	private JobService jobService;
//	@Autowired
//	private TaskExecutor taskExecutor;
//	/**
//	 * 初始化一个定时删除日志的任务
//	 */
//	@PostConstruct
//	public void initDeleteLogsJob() {
//		taskExecutor.execute(() -> {
//			Job job = new Job();
//			job.setJobName("delete-logs-job");
//			job.setCron("0 0 0 * * ?");
//			job.setDescription("定时删除三个月前日志");
//			job.setSpringBeanName("sysLogServiceImpl");
//			job.setMethodName("deleteLogs");
//			job.setIsSysJob(true);
//			job.setStatus(1);
//			jobService.saveJob(job);
//		});
//	}
}