package com.hjrpc.concurrent.JobFrame;

public interface ITaskProcesser<T,R> {

   <R> Result<R> taskExecute(T param);

}
