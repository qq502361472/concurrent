package com.hjrpc.concurrent.JobFrame;

public class TestTask implements ITaskProcesser<Integer, String> {
    @Override
    public Result<String> taskExecute(Integer param) {
        try {
            Thread.sleep(param);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Result<String> result = null;
        if (param < 100) {
            result = new Result<>(ResultType.Success, param + "");
        } else if (param >= 100 && param < 120) {
            result = new Result<>(ResultType.Failure, param + "", "failure");
        } else {
            result = new Result<>(ResultType.Exception, param + "", "exception");
        }
//        System.out.println(result);
        return result;
    }
}
