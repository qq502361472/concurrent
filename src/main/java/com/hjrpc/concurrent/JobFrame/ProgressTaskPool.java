package com.hjrpc.concurrent.JobFrame;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ProgressTaskPool<T, R> {

    //懒汉式单例
    private ProgressTaskPool() {
    }

    private static class ProgressPoolHolder {
        private static ProgressTaskPool progressTaskPool = new ProgressTaskPool();
    }

    public static ProgressTaskPool getInstance() {
        return ProgressPoolHolder.progressTaskPool;
    }
    //懒汉式单例

    private static final int coreNum = Runtime.getRuntime().availableProcessors();

    private static final BlockingQueue<Runnable> blockingQueue
            = new LinkedBlockingQueue<>(5000);

    private static final ThreadPoolExecutor pool
            = new ThreadPoolExecutor(coreNum, coreNum, 60
            , TimeUnit.SECONDS, blockingQueue);

    private static final ConcurrentHashMap<String, JobInfo<?, ?>> map = new ConcurrentHashMap<>();

    private static CheckJobProcesser  checkJobProcesser = CheckJobProcesser.getInstance();


    public void registerJob(String jobName, ITaskProcesser<T, R> taskProcesser
            , Integer jobSize, Long expireTime) {
        JobInfo<T, R> jobInfo = new JobInfo<T, R>(jobName, taskProcesser, jobSize, expireTime);
        if (map.putIfAbsent(jobName, jobInfo) != null) {
            throw new RuntimeException("task " + jobName + " is registed!");
        }
    }

    public void pushTask(String jobName, T param) {
        JobInfo<T, R> jobInfo = (JobInfo<T, R>) map.get(jobName);
        pool.execute(new ProgressTask(jobInfo, param));
    }

    public static String getProgress(String jobName){
        JobInfo<?, ?> jobInfo = map.get(jobName);
        if(jobInfo==null){
            return null;
        }
        return jobInfo.getProcesser();
    }

    public static Map<String, JobInfo<?, ?>> getMap(){
        return map;
    }

    private class ProgressTask implements Runnable {
        JobInfo<T, R> jobInfo;
        T param;

        public ProgressTask(JobInfo<T, R> jobInfo, T param) {
            this.jobInfo = jobInfo;
            this.param = param;
        }

        @Override
        public void run() {
            ITaskProcesser<T, R> taskProcesser = jobInfo.getTaskProcesser();
            Result<R> result = null;
            try {
                result = taskProcesser.taskExecute(param);
                if (result == null) {
                    result = new Result<R>(ResultType.Exception, null, "result is null");
                }

                if (result.getResultType() == null) {
                    if (result.getReson() == null) {
                        result = new Result<R>(ResultType.Exception, null, "reson is null");
                    } else {
                        result = new Result<R>(ResultType.Exception, null, result.getReson());
                    }
                }
            } catch (Exception e) {
                result = new Result<R>(ResultType.Exception, null, e.getMessage());
                e.printStackTrace();
            } finally {
                jobInfo.addResult(result,checkJobProcesser);
            }
        }
    }
}
