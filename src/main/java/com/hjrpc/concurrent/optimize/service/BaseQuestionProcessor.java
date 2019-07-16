package com.hjrpc.concurrent.optimize.service;

import com.hjrpc.concurrent.optimize.utils.SimulateBuinessUtil;

import java.util.Random;

public class BaseQuestionProcessor {

    /**
     * 对题目进行处理，如解析文本，下载图片等等工作
     * @param questionId 题目id
     * @return 题目解析后的文本
     */
    public static String makeQuestion(Integer questionId, String detail) {
        Random r = new Random();
        SimulateBuinessUtil.buinessMilliseconds(450+r.nextInt(100));
        return "CompleteQuestion[id="+questionId
                +" content=:"+ detail+"]";
    }
}
