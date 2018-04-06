package fr.khady.batchRelance;

import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class BatchScheduler {

	   @Bean
	    public SimpleJobLauncher jobLauncher(JobRepository jobRepository) {
	        SimpleJobLauncher launcher = new SimpleJobLauncher();
	        launcher.setJobRepository(jobRepository);
	        return launcher;
	    }

}
