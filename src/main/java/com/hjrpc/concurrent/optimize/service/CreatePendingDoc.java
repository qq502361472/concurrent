package com.hjrpc.concurrent.optimize.service;

import com.hjrpc.concurrent.optimize.constant.Constant;
import com.hjrpc.concurrent.optimize.vo.SrcDocVo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CreatePendingDoc {

    public static List<SrcDocVo> createPendingDoc(int size){
        List<SrcDocVo> list = new LinkedList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            List<Integer> questions = new LinkedList<>();
            for (int j = 0; j < Constant.QUESTION_COUNT_INDOC; j++) {
                questions.add(random.nextInt(Constant.QUESTION_SIZE));
            }

            list.add(new SrcDocVo("pending_"+i,questions));
        }
        return list;
    }
}
