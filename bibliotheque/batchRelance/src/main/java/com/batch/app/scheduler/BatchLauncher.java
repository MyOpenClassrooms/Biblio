package com.batch.app.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.batch.app.mail.ReservationMail;

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
	  final Logger log = LoggerFactory.getLogger(ReservationMail.class);
    JobParameters parameters = new JobParametersBuilder()
        .addLong("currentTime", new Long(System.currentTimeMillis()))
        .toJobParameters();
    try {
      log.info("********************* Start Job: " + rappeljob +" *************************");
      jobLauncher.run(rappeljob, parameters);
      log.info("********************* Start Job: " + job +" *************************");
      jobLauncher.run(job, parameters);
    } catch (JobExecutionAlreadyRunningException e) {
    } catch (JobRestartException e) {
    } catch (JobInstanceAlreadyCompleteException e) {
    } catch (JobParametersInvalidException e) {
    }
  }
}
