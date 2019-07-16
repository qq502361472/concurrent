package com.hjrpc.concurrent.optimize.vo;

import java.util.concurrent.Future;

public class MakeDocTaskResultVo {
    private final QuestionInCacheVo cacheVo;
    private final Future<QuestionInCacheVo> future;

    public MakeDocTaskResultVo(Future<QuestionInCacheVo> future) {
        this.cacheVo = null;
        this.future = future;
    }
    public MakeDocTaskResultVo(QuestionInCacheVo cacheVo) {
        this.cacheVo = cacheVo;
        this.future = null;
    }

    public QuestionInCacheVo getCacheVo() {
        return cacheVo;
    }

    public Future<QuestionInCacheVo> getFuture() {
        return future;
    }
}
