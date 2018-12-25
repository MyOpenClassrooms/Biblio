package com.batch.app.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BatchLauncher {
 
  @Autowired
  @Qualifier("scheduledJob")
  private Job job;
 
  @Autowired
  @Qualifier("rappelFiveDaysJob")
  private Job rappeljob;
  
  @Autowired
  private JobLauncher jobLauncher;
 
  public void run() {
    JobParameters parameters = new JobParametersBuilder()
        .addLong("currentTime", new Long(System.currentTimeMillis()))
        .toJobParameters();
    try {
      jobLauncher.run(rappeljob, parameters);
      jobLauncher.run(job, parameters);
    } catch (JobExecutionAlreadyRunningException e) {
    } catch (JobRestartException e) {
    } catch (JobInstanceAlreadyCompleteException e) {
    } catch (JobParametersInvalidException e) {
    }
  }
}
