package com.example.scheduler.timer;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class ImporterScheduler {

	private static final Logger logger = LoggerFactory.getLogger(ImporterScheduler.class);

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@Scheduled(cron = "${scheduler.cron:*/42 * * * * *}")	
	public void run() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		logger.info("Starting bulk file importing cron.");		
		String executionUuid = UUID.randomUUID().toString();
		MDC.put("trace-id", executionUuid);
		// para que a job possa ser executada multiplas vezes deve-se passar um
		// paramatro com um valor para cada execucao da mesma
		//spring batch armazena as jobs no banco por default, verificar o status das mesmas
		JobParametersBuilder builder = new JobParametersBuilder().addString(executionUuid, UUID.randomUUID().toString());
		JobParameters jobParameters = builder.toJobParameters();
		jobLauncher.run(job, jobParameters);
		stopWatch.stop();
		logger.info("Finishing bulk file importing cron. Job executed in {} ms", stopWatch.getTotalTimeSeconds());
		MDC.clear();

	}

}
