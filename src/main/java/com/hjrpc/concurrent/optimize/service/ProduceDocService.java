package com.hjrpc.concurrent.optimize.service;

import com.hjrpc.concurrent.optimize.utils.SimulateBuinessUtil;
import com.hjrpc.concurrent.optimize.vo.MakeDocTaskResultVo;
import com.hjrpc.concurrent.optimize.vo.SrcDocVo;

import java.util.Random;
import java.util.concurrent.ExecutionException;

public class ProduceDocService {


    public static String makeDoc(SrcDocVo doc) {
        System.out.println("开始处理文档："+ doc.getDocName());
        StringBuffer sb = new StringBuffer();
        //循环处理文档中的每个题目
        for(Integer questionId: doc.getQuestionList()){
//            sb.append(SingleQstService.makeQuestion(questionId));
            MakeDocTaskResultVo resultVo = ParallerQstService.makeQuestion(questionId);
            try {
                sb.append(resultVo.getCacheVo()==null?resultVo.getFuture().get():resultVo.getCacheVo().getDetail());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return "complete_"+System.currentTimeMillis()+"_"
                + doc.getDocName()+".pdf";
    }

    public static String upLoadDoc(String docFileName) {
        Random r = new Random();
        SimulateBuinessUtil.buinessMilliseconds(9000+r.nextInt(400));
        return "http://www.xxxx.com/file/upload/"+docFileName;
    }
}
