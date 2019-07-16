package com.hjrpc.concurrent.JobFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class JobInfo<T,R> {
    private final String jobName;
    private final ITaskProcesser<T,R> taskProcesser;
    private final AtomicInteger jobSize;
    private final AtomicInteger successCount;
    private final AtomicInteger totalProcesserCount;
    private final LinkedBlockingDeque<Result<R>> resultDeque;
    private final Long expireTime;

    public JobInfo(String jobName,ITaskProcesser<T, R> taskProcesser, Integer jobSize, Long expireTime) {
        this.jobName = jobName;
        this.taskProcesser = taskProcesser;
        this.jobSize = new AtomicInteger(jobSize);
        this.successCount = new AtomicInteger(0);
        this.totalProcesserCount = new AtomicInteger(0);
        this.resultDeque = new LinkedBlockingDeque<>();
        this.expireTime = expireTime;
    }


    public void addResult(Result<R> result, CheckJobProcesser checkJobProcesser){
        if(ResultType.Success.equals(result.getResultType())){
            successCount.incrementAndGet();
        }
        totalProcesserCount.incrementAndGet();
        resultDeque.addFirst(result);
        if(totalProcesserCount.get()==jobSize.get()) {
            checkJobProcesser.putJob(jobName, expireTime);
        }
    }

    public List<Result<R>> getResultDetail(){
        List<Result<R>> resultList = new ArrayList<>();
        Result<R> result;
        while ((result = resultDeque.pollLast())!=null){
            resultList.add(result);
        }
        return resultList;
    }

    public String getJobName() {
        return jobName;
    }

    public ITaskProcesser<T, R> getTaskProcesser() {
        return taskProcesser;
    }

    public AtomicInteger getJobSize() {
        return jobSize;
    }

    public AtomicInteger getSuccessCount() {
        return successCount;
    }

    public AtomicInteger getTotalProcesserCount() {
        return totalProcesserCount;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public String getProcesser(){
        return "["+jobName+"]"+successCount.get()+"/"+(totalProcesserCount.get()-successCount.get())
                +"/"+totalProcesserCount.get()+"/"+jobSize;
    }
}
