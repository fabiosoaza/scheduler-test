package com.example.scheduler.parser;

import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;

public final class SMSFixedLengthLineTokenizer extends FixedLengthTokenizer {

	{
		setNames(new String[] { SMSFieldSetMapper.MOBILE, SMSFieldSetMapper.ID, SMSFieldSetMapper.SCHEDULE, SMSFieldSetMapper.MSG });
		//5551999999999 5                                 06/05/2017 14:26:22   Message 5
		
		Range mobileRange = new Range(1, 14);
		Range externalKeyRange = new Range(15, 54);
		Range scheduleRange = new Range(55, 76);
		Range msgRange = new Range(77);
		
		setColumns(new Range[]{mobileRange, externalKeyRange, scheduleRange, msgRange});
	
	}
}