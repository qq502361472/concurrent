package com.hjrpc.concurrent.optimize;

import com.hjrpc.concurrent.optimize.constant.Constant;
import com.hjrpc.concurrent.optimize.utils.SHAUtil;
import com.hjrpc.concurrent.optimize.utils.SimulateBuinessUtil;
import com.hjrpc.concurrent.optimize.vo.QuestionInDbVo;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class QuestionRepository {

    private static ConcurrentHashMap<Integer, QuestionInDbVo> repositroyMap =
            new ConcurrentHashMap<>();

    private static Random random = new Random();
    private static final Integer strLength= Constant.ALL_CHAR_STRING.length();

    private static ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);

    public static void init(){
        for (int i = 0; i < Constant.QUESTION_SIZE; i++) {
            String questionDetail = makeQuestionDetail();
            try {
                repositroyMap.put(i,new QuestionInDbVo(i, questionDetail, SHAUtil.eccrypt(questionDetail,"SHA")));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        startUpdateThread();
    }

    private static void startUpdateThread() {
        scheduledExecutorService.scheduleAtFixedRate(()->{
            int questionId = random.nextInt(Constant.QUESTION_SIZE);
            String questionDetail = makeQuestionDetail();
            try {
                repositroyMap.put(questionId,new QuestionInDbVo(questionId,questionDetail,SHAUtil.eccrypt(questionDetail,"SHA")));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }finally {
                System.out.println("题目["+questionId+"]已经被更新...");
            }

        },0,5, TimeUnit.SECONDS);
    }

    private static String makeQuestionDetail() {
        int len = Constant.QUESTION_LENGTH + random.nextInt(100);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            sb.append(Constant.ALL_CHAR_STRING.charAt(random.nextInt(strLength)));
        }
        return sb.toString();
    }

    public static QuestionInDbVo getQuestion(Integer questionId) {
        SimulateBuinessUtil.buinessMilliseconds(20);
        return repositroyMap.get(questionId);
    }

    public static String getSha1(Integer questionId) {
        SimulateBuinessUtil.buinessMilliseconds(10);
        return repositroyMap.get(questionId).getSha();
    }
}
