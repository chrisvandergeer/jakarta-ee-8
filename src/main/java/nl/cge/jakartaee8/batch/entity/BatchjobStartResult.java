package nl.cge.jakartaee8.batch.entity;

import java.util.Date;

public class BatchjobStartResult {

    private Long jobId;
    private Date started;

    public BatchjobStartResult(Long jobId) {
        this.jobId = jobId;
        this.started = new Date();
    }

    public Long getJobId() {
        return jobId;
    }

    public Date getStarted() {
        return started;
    }
}
