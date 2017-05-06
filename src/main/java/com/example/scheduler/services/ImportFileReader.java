package com.example.scheduler.services;

import java.net.MalformedURLException;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.core.io.UrlResource;

import com.example.scheduler.domain.SMS;

public class ImportFileReader {

	
	private LineMapper<SMS> mapper;
	private String path;
	
	
	public ImportFileReader(LineMapper<SMS> mapper, String path) {
		super();
		this.mapper = mapper;
		this.path = path;
	}

	public ImportFileReader() {
		// TODO Auto-generated constructor stub
	}
	
	public ItemReader<SMS> reader() {
		
		FlatFileItemReader<SMS> reader = new FlatFileItemReader<SMS>();
		UrlResource resource=getResource(path);
		reader.setResource(resource);		
		reader.setLineMapper(mapper);
		return reader;
	}

	private static UrlResource getResource(String path) {
		UrlResource resource=null;
		try {
			resource = new UrlResource(path);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
		return resource;
	}

	
	
}