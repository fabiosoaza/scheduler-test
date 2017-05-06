package com.example.scheduler.parser;

import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.stereotype.Component;

import com.example.scheduler.domain.SMS;

@Component
public class SMSLineMapperFactory {

	public enum Layout {
		E, FIXED
	}

	public DefaultLineMapper<SMS> getMapper(Layout layout) {
		DefaultLineMapper<SMS> mapper = null;
		if (layout == Layout.E) {
			mapper = new SMSDelimitedLineMapper();
		} else if (layout == Layout.FIXED) {
			mapper = new SMSFixedColumnLineMapper();
		}
		return mapper;
	}

	private static final class SMSDelimitedLineMapper extends DefaultLineMapper<SMS> {
		{
			setLineTokenizer(new SMSDelimitedLineTokenizer());
			setFieldSetMapper(new SMSFieldSetMapper());
		}
	}

	private static final class SMSFixedColumnLineMapper extends DefaultLineMapper<SMS> {
		{
			setLineTokenizer(new SMSFixedLengthLineTokenizer());
			setFieldSetMapper(new SMSFieldSetMapper());
		}
	}

}
