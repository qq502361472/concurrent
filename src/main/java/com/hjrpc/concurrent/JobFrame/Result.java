package com.hjrpc.concurrent.JobFrame;

public class Result<R> {
    private final ResultType resultType;
    private final R r;
    private final String reson;

    public Result(ResultType resultType, R r, String reson) {
        this.resultType = resultType;
        this.r = r;
        this.reson = reson;
    }

    public Result(ResultType resultType, R r) {
        this.resultType = resultType;
        this.r = r;
        this.reson = "Success";
    }

    public ResultType getResultType() {
        return resultType;
    }

    public R getR() {
        return r;
    }

    public String getReson() {
        return reson;
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultType=" + resultType +
                ", r=" + r +
                ", reson='" + reson + '\'' +
                '}';
    }
}
