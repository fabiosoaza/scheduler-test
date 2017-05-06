package com.example.scheduler.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.scheduler.domain.SMS;

@Component
public class ImportFileProcessor implements ItemProcessor<SMS, SMS> {

	private static final Logger logger = LoggerFactory.getLogger(ImportFileProcessor.class);

	@Override
	public SMS process(SMS customer) throws Exception {

		final SMS fixedCustomer = new SMS(customer.getMobile(), customer.getMsg().toUpperCase(), customer.getExternalId(),customer.getSender(), customer.getDate());

		//logger.info("Converting (" + customer + ") into (" + fixedCustomer + ")");

		return fixedCustomer;
	}
}
