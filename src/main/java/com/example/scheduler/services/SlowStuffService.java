package com.example.scheduler.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.scheduler.config.CacheNames;

@CacheConfig()
@Service
public class SlowStuffService {

	private static final Logger logger = LoggerFactory.getLogger(SlowStuffService.class);
	
	@Cacheable(cacheNames=CacheNames.SLOW_STUFF)
	public void doStuff(){		
		try {
			logger.info("Starting slow stuff");
			Thread.sleep(3000);
			logger.info("Ending slow stuff");
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
}
