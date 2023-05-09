package com.learning.domains.scheduler_jobs.config;

import com.learning.domains.scheduler_jobs.Jobs;
import com.learning.domains.scheduler_jobs.job_repositories.JobRepository;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class JobConfig {

    @Resource
    private JobRepository jobRepository;

    @Resource
    private SchedulerFactoryBean schedulerFactory;

    @Bean
    public Scheduler scheduler() throws SchedulerException {
        Scheduler scheduler = schedulerFactory.getScheduler();
        List<Jobs> jobs = jobRepository.findAllActiveJobs();
        for (Jobs job : jobs) {
            JobDetail jobDetail = JobBuilder.newJob(getClass(job.getJobClass()))
                    .withIdentity(job.getJobName(), job.getJobGroup())
                    .build();
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(job.getJobName(), job.getJobGroup())
                    .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()))
                    .build();
            scheduler.scheduleJob(jobDetail, cronTrigger);
        }
        scheduler.start();
        return scheduler;
    }

    private Class<? extends Job> getClass(String className) {
        try {
            return (Class<? extends Job>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
