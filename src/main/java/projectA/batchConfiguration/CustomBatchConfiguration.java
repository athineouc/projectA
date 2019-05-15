package projectA.batchConfiguration;


import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Value;
import projectA.customItemProcessor.CustomItemProcessor;
import projectA.customItemReader.CustomItemReader;
import projectA.customItemWriter.CustomItemWriter;
import projectA.model.Person;
import projectA.validators.LineValidator;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static projectA.constants.Constants.*;

@Configuration
@EnableBatchProcessing
public class CustomBatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    @Bean
    @StepScope
    public FlatFileItemReader<Person> customReader( @Value("#{jobParameters['fileName']}") String filePath ) {
        CustomItemReader customItemReader = new CustomItemReader();
        return customItemReader.initializeReader(filePath);
    }


    @Bean
    public CustomItemProcessor customProcessor() {
        return new CustomItemProcessor();
    }

    @Bean
    public CustomItemWriter customWriter() {
        return new CustomItemWriter();
    }


    @Bean
    public Job customJob() {
        return jobBuilderFactory.get(JOB_BUILDER)
                .incrementer(new RunIdIncrementer())
                .flow(customStep())
                .end()
                .build();
    }

    @Bean
    public Step customStep() {
        return stepBuilderFactory.get(STEP_BUILDER)
                .<Person, Person>chunk(CHUNK_SIZE)
                .reader(customReader(null)).faultTolerant().skipPolicy(new LineValidator())
                .processor(customProcessor())
                .writer(customWriter())
                .build();
    }
}
