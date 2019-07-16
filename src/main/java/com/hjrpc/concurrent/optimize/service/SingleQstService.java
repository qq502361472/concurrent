package com.hjrpc.concurrent.optimize.service;

import com.hjrpc.concurrent.optimize.QuestionRepository;

public class SingleQstService {
    public static String makeQuestion(Integer questionId) {
        return BaseQuestionProcessor.makeQuestion(questionId,
                QuestionRepository.getQuestion(questionId).getDetail());
    }
}
