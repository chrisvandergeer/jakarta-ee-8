package nl.cge.jakartaee8.batch.control.jobstatus;

import nl.cge.jakartaee8.batch.entity.MyEntity;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.JobInstance;
import java.util.*;
import java.util.stream.Collectors;

public class JobStatusController {

    public List<JobStatusDto> getStatussen() {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        return jobOperator.getJobNames().stream()
                .flatMap(jobName -> jobOperator.getJobInstances(jobName, 0, 25).stream())
                .map(jobInstance -> jobOperator.getJobExecution(jobInstance.getInstanceId()))
                .map(this::map)
                .collect(Collectors.toList());
    }

    private JobStatusDto map(JobExecution jobExecution) {
        JobStatusDto dto = new JobStatusDto();
        dto.setJobName(jobExecution.getJobName());
        dto.setBatchStatus(jobExecution.getBatchStatus().name());
        dto.setExitStatus(jobExecution.getExitStatus());
        dto.setStartTime(jobExecution.getStartTime());
        dto.setEndTime(jobExecution.getEndTime());
        return dto;
    }
}
