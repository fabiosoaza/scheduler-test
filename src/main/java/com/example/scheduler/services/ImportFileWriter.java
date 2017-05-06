package com.example.scheduler.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.example.scheduler.domain.SMS;
import com.example.scheduler.repository.SMSRepository;

@Component
public class ImportFileWriter implements ItemWriter<SMS> {

	private static final Logger logger = LoggerFactory.getLogger(ImportFileWriter.class);

	@Autowired
	private SMSRepository smsRepository;

	@Override
	public void write(List<? extends SMS> sms) throws Exception {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		smsRepository.save(sms);
		stopWatch.stop();
		logger.info("Writing {} messages in database in {} ms", sms.size(), stopWatch.getTotalTimeMillis());
	}

}
