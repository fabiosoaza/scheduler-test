package com.example.scheduler.parser;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

public class SMSDelimitedLineTokenizer extends DelimitedLineTokenizer {
	
	static final String SEMI_COLON = ";";

	{
		setNames(new String[] { SMSFieldSetMapper.MOBILE, SMSFieldSetMapper.MSG, SMSFieldSetMapper.ID, SMSFieldSetMapper.SENDER, SMSFieldSetMapper.SCHEDULE });
		setDelimiter(SEMI_COLON);
	}
}