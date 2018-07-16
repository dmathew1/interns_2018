package rahul_jenn.v3_springData.util;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import rahul_jenn.Loggable;

/**
 * Created by denze on 7/15/2018.
 */
@Component
public class StartupApplicationListener implements ApplicationRunner, Loggable {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        logger().info("I AM HEREEEE");
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time",System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(job,jobParameters);

    }
}
