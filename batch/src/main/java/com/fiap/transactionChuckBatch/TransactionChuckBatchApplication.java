package com.fiap.transactionChuckBatch;

import com.fiap.transactionChuckBatch.entity.StudentEntity;
import com.fiap.transactionChuckBatch.listener.JobCompletionNotificationListener;
import com.fiap.transactionChuckBatch.dto.StudentDTO;
import com.fiap.transactionChuckBatch.processor.StudentItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.logging.Logger;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableBatchProcessing
public class TransactionChuckBatchApplication {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	private Logger logger = Logger.getLogger(TransactionChuckBatchApplication.class.getSimpleName());

	public static void main(String[] args) {
		SpringApplication.run(TransactionChuckBatchApplication.class, args);
	}

	@Bean
	public FlatFileItemReader<StudentDTO> reader() {
		return new FlatFileItemReaderBuilder<StudentDTO>().name("userItemReader")
				//.resource(new ClassPathResource("listagem_aluno.csv"))
				.resource(new ClassPathResource("lista_alunos.csv"))
				.delimited()
				.names(new String[] {"nome", "ra", "email"})
				.fieldSetMapper(new BeanWrapperFieldSetMapper<StudentDTO>() {
					{
						setTargetType(StudentDTO.class);
					}
				}).build();
	}



	@Bean
	public StudentItemProcessor processor() {
		return new StudentItemProcessor();
	}

	@Bean
	public MongoItemWriter<StudentEntity> writer(MongoTemplate mongoTemplate) {
		return new MongoItemWriterBuilder<StudentEntity>().template(mongoTemplate).collection("students")
				.build();
	}
	@Bean
	public Step step1(FlatFileItemReader<StudentDTO> itemReader, MongoItemWriter<StudentEntity> itemWriter)
			throws Exception {

		return this.stepBuilderFactory.get("step1").<StudentDTO, StudentEntity>chunk(5).reader(itemReader)
				.processor(processor()).writer(itemWriter).build();
	}

	@Bean
	public Job updateUserJob(JobCompletionNotificationListener listener, Step step1)
			throws Exception {

		return this.jobBuilderFactory.get("updateUserJob").incrementer(new RunIdIncrementer())
				.listener(listener).start(step1).build();
	}

}
