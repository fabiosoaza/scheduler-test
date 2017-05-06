package com.example.scheduler.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.scheduler.domain.SMS;
import com.example.scheduler.parser.SMSLineMapperFactory;
import com.example.scheduler.parser.SMSLineMapperFactory.Layout;
import com.example.scheduler.processor.ImportFileProcessor;
import com.example.scheduler.services.ImportFileJobListener;
import com.example.scheduler.services.ImportFileReader;
import com.example.scheduler.services.ImportFileWriter;

@Configuration
@EnableBatchProcessing
public class ImportFileJobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private ImportFileWriter importFileWriter;

	@Autowired
	private ImportFileProcessor importFileProcessor;

	@Autowired
	private SMSLineMapperFactory smsLineMapperFactory;

	@Bean
	public Job job() {
		return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer()).listener(new ImportFileJobListener())
				.flow(step1()).end().build();
	}

	@Bean
	public Step step1() {		
		Layout layout = Layout.FIXED;
		ImportFileReader reader = getReaderByLayout(layout);
		return stepBuilderFactory
				.get("step1")
				.<SMS, SMS>chunk(100)
				.reader(reader.reader())
				.processor(importFileProcessor)
				.writer(importFileWriter)
				.build();
	}

	private ImportFileReader getReaderByLayout(Layout layout) {
		final String path;
		if (layout == Layout.E) {
			path = "file:./files/layout_e.csv";
		} else {
			path = "file:./files/layout_fixado.txt";
		}

		DefaultLineMapper<SMS> mapper = smsLineMapperFactory.getMapper(layout);
		ImportFileReader reader = new ImportFileReader(mapper, path);
		return reader;
	}
}
