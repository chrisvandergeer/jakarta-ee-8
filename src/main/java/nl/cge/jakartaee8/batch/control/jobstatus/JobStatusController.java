package nl.cge.jakartaee8.batch.control.jobstatus;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import java.util.*;
import java.util.stream.Collectors;

public class JobStatusController {

    public Map<String, List<String>> getStatussen() {
        Map<String, List<String>> result = new HashMap<>();
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        Set<String> jobNames = jobOperator.getJobNames();
        jobNames.forEach(jobName -> {
            result.put(jobName, new ArrayList<>());
        });
        result.keySet().forEach(jobName -> {
            jobOperator.getJobInstances(jobName, 0, 25).stream()
                    .forEach(jobInstance -> {
                        JobExecution jobExecution = jobOperator.getJobExecution(jobInstance.getInstanceId());
                        String status = String.format("id = %s, name = %s, status = %s, exittatus = %s, starttime = %s, endtime = %s",
                                jobExecution.getExecutionId(),
                                jobExecution.getJobName(),
                                jobExecution.getBatchStatus(),
                                jobExecution.getExitStatus(),
                                jobExecution.getStartTime(),
                                jobExecution.getEndTime());
                        result.get(jobName).add(status);
                    });
        });
        return result;
    }

    public List<String> getStatus() {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        Set<String> jobNames = jobOperator.getJobNames();
        return jobNames.stream().map(name ->
                jobOperator.getRunningExecutions(name).stream()
                        .map(jobid -> jobOperator.getJobExecution(jobid))
                        .map(job -> String.format("job %s, status: %s", name, job.getBatchStatus()))
                        .findAny().orElse(String.format("job %s, status: unknown", name))
        ).collect(Collectors.toList());
    }
}
