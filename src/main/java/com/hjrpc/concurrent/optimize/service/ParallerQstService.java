package com.hjrpc.concurrent.optimize.service;

import com.hjrpc.concurrent.optimize.QuestionRepository;
import com.hjrpc.concurrent.optimize.constant.Constant;
import com.hjrpc.concurrent.optimize.vo.MakeDocTaskResultVo;
import com.hjrpc.concurrent.optimize.vo.QuestionInCacheVo;
import com.hjrpc.concurrent.optimize.vo.QuestionInDbVo;

import java.util.concurrent.*;

public class ParallerQstService {

    private static final ConcurrentHashMap<Integer, QuestionInCacheVo> cachedMap
            = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<Integer, Future<QuestionInCacheVo>> processingMap
            = new ConcurrentHashMap<>();

    private static final ExecutorService pool = Executors.newFixedThreadPool(Constant.CPU_CORE_COUNT*2);

    public static MakeDocTaskResultVo makeQuestion(Integer questionId) {
        QuestionInCacheVo cacheVo = cachedMap.get(questionId);
        if(cacheVo!=null){
            String questionSha = QuestionRepository.getSha1(questionId);
            if(cacheVo.getSha().equals(questionSha)){
                System.out.println("题目["+questionId+"]在缓存中,且未被更新");
                return new MakeDocTaskResultVo(cacheVo);
            }else{
                return new MakeDocTaskResultVo(getFuture(questionId));
            }
        }else{
            return new MakeDocTaskResultVo(getFuture(questionId));
        }
    }

    private static Future<QuestionInCacheVo> getFuture(Integer questionId) {
        makeDoc makeDoc = new makeDoc(questionId);
        FutureTask<QuestionInCacheVo> futureTask = new FutureTask<>(makeDoc);

        Future<QuestionInCacheVo> future = processingMap.putIfAbsent(questionId, futureTask);
        if(future==null){
            System.out.println("题目["+questionId+"]不在缓存中,且没有线程处理");
            future = futureTask;
            pool.submit(futureTask);
        }else {
//            System.out.println("题目["+questionId+"]不在缓存中,但有线程正在处理");
        }
        return future;

    }

    private static class makeDoc implements Callable<QuestionInCacheVo>{
        private Integer questionId;

        public makeDoc(Integer questionId) {
            this.questionId = questionId;
        }

        @Override
        public QuestionInCacheVo call() throws Exception {
            try {
                QuestionInDbVo question = QuestionRepository.getQuestion(questionId);
                String localUrl = BaseQuestionProcessor.makeQuestion(questionId,
                        question.getDetail());
                QuestionInCacheVo cacheVo = new QuestionInCacheVo(questionId, question.getDetail(), question.getSha());
                cachedMap.put(questionId,cacheVo);
                return cacheVo;
            } finally {
                //防止再次更新此题目
                processingMap.remove(questionId);
            }
        }
    }

}
