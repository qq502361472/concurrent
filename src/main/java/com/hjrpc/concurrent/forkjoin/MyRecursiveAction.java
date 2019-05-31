package com.hjrpc.concurrent.forkjoin;

import java.io.File;
import java.util.concurrent.RecursiveAction;

/**
 * 无返回值 forkjoin
 */
public class MyRecursiveAction extends RecursiveAction {

    private File source;

    public MyRecursiveAction(File source) {
        this.source = source;
    }

    @Override
    protected void compute() {
        if (source == null) {
            return;
        }

        File[] files = source.listFiles();
        if(files==null||files.length==0){
            return;
        }
        for (File file : files) {
            if(file.isDirectory()){
                MyRecursiveAction recursiveAction = new MyRecursiveAction(file);
                //异步处理
                recursiveAction.fork();
                recursiveAction.join();
            }else {
                String absolutePath = file.getAbsolutePath();
                if(absolutePath.endsWith(".txt")){
                    System.out.println(absolutePath);
                }
            }
        }
    }
}
