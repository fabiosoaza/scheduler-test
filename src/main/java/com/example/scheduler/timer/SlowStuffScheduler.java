package com.example.scheduler.timer;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.scheduler.services.SlowStuffService;


@Component
public class SlowStuffScheduler {

	private static final Logger logger = LoggerFactory.getLogger(SlowStuffScheduler.class);

	@Autowired
	private SlowStuffService slowStuff;
	
	//@Scheduled(cron="${scheduler.cron:*/10 * * * * *}")
	public void doStuff(){
		MDC.put("trace-id", UUID.randomUUID().toString());
		logger.info("Executando");
		slowStuff.doStuff();
		MDC.clear();
	}
	
}
