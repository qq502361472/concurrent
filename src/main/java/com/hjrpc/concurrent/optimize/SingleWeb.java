package com.hjrpc.concurrent.optimize;

import com.hjrpc.concurrent.optimize.service.CreatePendingDoc;
import com.hjrpc.concurrent.optimize.service.ProduceDocService;
import com.hjrpc.concurrent.optimize.vo.SrcDocVo;

import java.util.List;

/**
 * 分离出需要处理的题目(60~120个，平均大约80个题目左右)
 * 解析处理题目文本，对题目中的图片下载到本地，然后调用第三方工具生成PDF文档（耗时大约40~45秒）
 * 将PDF文档上传到云空间进行存储（耗时大约9、10秒）
 * 提供文档地址让用户去下载打印
 */
public class SingleWeb {
    public static void main(String[] args) {
        System.out.println("题库初始化开始....");
        QuestionRepository.init();
        System.out.println("题库初始化完成....");

        List<SrcDocVo> pendingDoc = CreatePendingDoc.createPendingDoc(2);

        long startTotal = System.currentTimeMillis();
        for(SrcDocVo doc:pendingDoc){
            long start = System.currentTimeMillis();
            String localName = ProduceDocService.makeDoc(doc);
            System.out.println("文档"+localName+"生成耗时："
                    +(System.currentTimeMillis()-start)+"ms");
            start = System.currentTimeMillis();
            String remoteUrl = ProduceDocService.upLoadDoc(localName);
            System.out.println("已上传至["+remoteUrl+"]耗时："
                    +(System.currentTimeMillis()-start)+"ms");
        }
        System.out.println("------------共耗时："
                +(System.currentTimeMillis()-startTotal)+"ms-------------");
    }
}
