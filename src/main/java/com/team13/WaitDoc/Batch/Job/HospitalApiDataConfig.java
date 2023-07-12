package com.team13.WaitDoc.Batch.Job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class HospitalApiDataConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    @Bean
    public Job testJob(){
        return jobBuilderFactory.get("testJob")
                .start(testStep1())
                .build();
    }
    @Bean
    @JobScope
    public Step testStep1(){
        return stepBuilderFactory.get("testStep1")
                .tasklet(testTasklet())
                .build();
    }
    @Bean
    @StepScope
    public Tasklet testTasklet() {
        return (contribution, chunkContext) -> {
            System.out.println(">>>>>>>>>>>>>>>>이건 테스트 입니다.");
            return RepeatStatus.FINISHED;
        };
    }
}
