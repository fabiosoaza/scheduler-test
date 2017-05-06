package com.example.scheduler.parser;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.example.scheduler.domain.SMS;

public class SMSFieldSetMapper implements FieldSetMapper<SMS> {
	
	public static final String SCHEDULE = "schedule";
	public static final String SENDER = "sender";
	public static final String ID = "id";
	public static final String MSG = "msg";
	public static final String MOBILE = "mobile";
	
	@Override
	public SMS mapFieldSet(FieldSet row) throws BindException {
		
		String mobile = readStringSafely(row, MOBILE);
		String msg = readStringSafely(row, MSG);
		String id = readStringSafely(row, ID);
		String sender = readStringSafely(row, SENDER);
		Date schedule = readDateSafely(row, SCHEDULE,  "dd/MM/yyyy HH:mm");
		return new SMS(mobile, msg, id, sender, schedule);
	}

	private String readStringSafely(FieldSet row, String columnName) {
		return contains(row, columnName) ? row.readRawString(columnName) : null;
	}
	
	private Date readDateSafely(FieldSet row, String columnName, String format) {
		return contains(row, columnName)? row.readDate(columnName, format) : null;
	}
	
	private boolean contains(FieldSet row, String columnName) {
		List<String> columnNames = Arrays.asList(row.getNames());
		return columnNames.contains(columnName);
	}
	
	
}