package fr.khady.batchRelance;


import java.util.Date;
import java.util.List;

import javax.batch.api.chunk.ItemReader;
import javax.batch.runtime.JobExecution;
import javax.mail.internet.MimeMessage;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.Scheduled;

import processor.ReservationItemProcessor;
import processor.UtilisateurItemProcessor;
import reader.ReservationItemReader;
import reader.UtilisateurItemReader;

@Configuration
@EnableBatchProcessing
@Import({BatchScheduler.class})
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private SimpleJobLauncher jobLauncher;
	
	@Value("${spring.mail.username}")
	private String sender;

	@Value("${codeurjc.batch.data}")
	public String data;

	// tag::readerwriterprocessor[]

	@Bean
	public org.springframework.batch.item.ItemReader<String> readerUser() {
		return new UtilisateurItemReader();
	}
	
	@Bean
	public org.springframework.batch.item.ItemReader<String> readerResa() {
		return new ReservationItemReader();
	}
	
	@Bean
	public UtilisateurItemProcessor processorUser() {
		return new UtilisateurItemProcessor(sender);
	}
	
	@Bean
	public ReservationItemProcessor processorResa() {
		return new ReservationItemProcessor(sender);
	}
	
	
	//envoi le mail à 14h 34 chaque jour
	@Scheduled(cron = "0 19		 11 ? * *")
	public void perform() throws Exception {

		System.out.println("Job Started at :" + new Date());

		JobParameters param = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();

		org.springframework.batch.core.JobExecution execution = jobLauncher.run(importResaJob(), param);

		System.out.println("Job finished with status :" + execution.getStatus());
	}
	

	
	@Bean
	public MailBatchItemWriter writer() {
		MailBatchItemWriter writer = new MailBatchItemWriter();
		return writer;
	}

//    @Bean
//    public Job importUserJob() {
//        return jobBuilderFactory.get("importUserJob")
//                .incrementer(new RunIdIncrementer())
//                .flow(step1())
//                .end()
//                .build();
//    }
//    
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .<String, MimeMessage> chunk(10)
//                .reader(readerUser())
//                .processor(processorUser())
//                .writer(writer())
//                .build();
//    }
    // end::jobstep[]
    
    @Bean
    public Job importResaJob() {
        return jobBuilderFactory.get("importResaJob")
                .incrementer(new RunIdIncrementer())
                .flow(stepR())
                .end()
                .build();
    }
    

    @Bean
    public Step stepR() {
        return stepBuilderFactory.get("stepR")
                .<String, MimeMessage> chunk(10)
                .reader(readerResa())
                .processor(processorResa())
                .writer(writer())
                .build();
    }
    // end::jobstep[]
}
