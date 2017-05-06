package com.example.scheduler.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Configuration;

import net.sf.ehcache.Cache;
import net.sf.ehcache.config.CacheConfiguration;

@EnableCaching
@Configuration
public class EHCacheConfiguration {

	@Value("${cache.slowStuff.timeToLiveInSeconds:50}")
	private Integer arbitraryPropertyTimeToLiveInSeconds;
	
	@Value("${cache.slowStuff.maxElementsInMemory:50}")
	private Integer slowStuffMaxEntriesLocalHeaplementsInMemory;
	
	@Autowired
	private EhCacheCacheManager ehCacheManager;
	
	@PostConstruct
	public void init(){
		net.sf.ehcache.CacheManager cacheManager = ehCacheManager.getCacheManager();	
		
		CacheConfiguration config = new CacheConfiguration();
		config.setTimeToLiveSeconds(arbitraryPropertyTimeToLiveInSeconds);
		config.setMaxEntriesLocalHeap(slowStuffMaxEntriesLocalHeaplementsInMemory);
		config.setName(CacheNames.SLOW_STUFF);
		
		Cache cache =  new Cache(config);		
		cacheManager.addCache(cache);
		
	
		
		
	}
	
	
	
	
	
	
}
