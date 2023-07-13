package com.team13.WaitDoc.Batch.Job;

import com.team13.WaitDoc.Batch.app.base.api.ApiUt;
import com.team13.WaitDoc.Batch.app.base.api.HospitalXml;
import com.team13.WaitDoc.Batch.app.hospital.entity.Department;
import com.team13.WaitDoc.Batch.app.hospital.entity.Hospital;
import com.team13.WaitDoc.Batch.app.hospital.entity.OperatingTime;
import com.team13.WaitDoc.Batch.app.hospital.repository.DepartmentRepository;
import com.team13.WaitDoc.Batch.app.hospital.repository.HospitalRepository;
import com.team13.WaitDoc.Batch.app.hospital.repository.OperatingTimeRepository;
import com.team13.WaitDoc.Batch.app.hospital.service.DepartmentService;
import com.team13.WaitDoc.Batch.app.hospital.service.HospitalService;
import com.team13.WaitDoc.Batch.app.hospital.service.OperatingTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.util.*;

@Configuration
@RequiredArgsConstructor
public class HospitalApiDataConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final HospitalService hospitalService;
    private final OperatingTimeService operatingTimeService;
    private final DepartmentService departmentService;
    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;
    private final OperatingTimeRepository operatingTimeRepository;
    @Bean
    public Job hospitalInitJob(){
        return jobBuilderFactory.get("testJob")
                .start(hospitalInitStep1())
                .build();
    }
    @Bean
    @JobScope
    public Step hospitalInitStep1(){
        return stepBuilderFactory.get("hospitalInitStep1")
                .tasklet(hospitalInitTasklet())
                .build();
    }
    @Bean
    @StepScope
    public Step hospitalInitStep2(ItemReader hospitalReader,
                                      ItemProcessor hospitalItemProcessor,
                                      ItemWriter hospitalItemWriter){
        return stepBuilderFactory.get("hospitalInitStep2")
                .<Hospital, Department>chunk(1000)
                .reader(hospitalReader)
                .processor(hospitalItemProcessor)
                .writer(hospitalItemWriter)
                .build();

    }
    @Bean
    @StepScope
    public Tasklet hospitalInitTasklet() {
        return (contribution, chunkContext) -> {
            List<HospitalXml.Item> items = ApiUt.Response.getItems(1, 1000);

            List<Hospital> hospitals = items.stream()
                    .map(hospitalService::toEntity)
                    .filter(Objects::nonNull)
                    .toList();
            hospitalService.saveAll(hospitals);

            List<OperatingTime> operatingTimes = items.stream()
                    .map(operatingTimeService::toEntity)
                    .filter(Objects::nonNull)
                    .toList();
            operatingTimeService.savaAll(operatingTimes);
            departmentService.saveAll(hospitals);
            return RepeatStatus.FINISHED;
        };
    }

    @StepScope
    @Bean
    public RepositoryItemReader<Hospital> hospitalReader() {

        return new RepositoryItemReaderBuilder<Hospital>()
                .name("hospitalReader")
                .repository(hospitalRepository)
                .methodName("findAll")
                .pageSize(1000)
                .arguments(Arrays.asList())
                .sorts(Collections.singletonMap("id", Sort.Direction.ASC))
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Hospital, List<Department>> hospitalItemProcessor() {
        return hospital -> {
            List<Department> departments = new ArrayList<>();
            for (String name : hospital.getDepartment().split(",")) {
                departments.add(
                        Department.builder()
                                .hospital(hospital)
                                .name(name)
                                .build()
                );
            }
            return departments;
        };
    }

    @StepScope
    @Bean
    public ItemWriter<List<Department>> orderItemWriter() {
        return items -> items.forEach(departmentRepository::saveAll);
    }


}
