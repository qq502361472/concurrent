package com.hjrpc.concurrent.optimize;

import com.hjrpc.concurrent.optimize.constant.Constant;
import com.hjrpc.concurrent.optimize.service.CreatePendingDoc;
import com.hjrpc.concurrent.optimize.service.ProduceDocService;
import com.hjrpc.concurrent.optimize.vo.SrcDocVo;

import java.util.List;
import java.util.concurrent.*;

//76958ms
public class ParallelWeb {

    private static  final ExecutorService makePool = Executors.newFixedThreadPool(Constant.CPU_CORE_COUNT*2);
    private static  final ExecutorService uploadPool = Executors.newFixedThreadPool(Constant.CPU_CORE_COUNT*2);

    private static final CompletionService<String> makeService = new ExecutorCompletionService<>(makePool);
    private static final CompletionService<String> uploadService = new ExecutorCompletionService<>(uploadPool);


    public static void main(String[] args) {

        System.out.println("题库初始化开始....");
        QuestionRepository.init();
        System.out.println("题库初始化完成....");

        List<SrcDocVo> pendingDoc = CreatePendingDoc.createPendingDoc(60);

        long startTotal = System.currentTimeMillis();
        for(SrcDocVo doc:pendingDoc){
            makeService.submit(()->{
                return ProduceDocService.makeDoc(doc);
            });
        }
        for(SrcDocVo doc:pendingDoc) {
            try {
                Future<String> take = makeService.take();
                uploadService.submit(()->{
                    return ProduceDocService.upLoadDoc(take.get());
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for(SrcDocVo doc:pendingDoc) {
            try {
                System.out.println("已上传至[" + uploadService.take().get() + "]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
        System.out.println("------------共耗时："
                + (System.currentTimeMillis() - startTotal) + "ms-------------");
    }


}
