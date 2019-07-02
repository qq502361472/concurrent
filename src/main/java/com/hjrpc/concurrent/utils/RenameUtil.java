package com.hjrpc.concurrent.utils;

import java.io.File;

public class RenameUtil {

    public static void main(String[] args) {
        String dir = "D:\\学习视频\\无密码享学\\";
        deleteEv4File(new File(dir));


    }

    private static void deleteEv4File(File file) {
        if(file==null){
            return;
        }
        String name = file.getName();
        if(file.isDirectory()){
            for (File listFile : file.listFiles()) {
                deleteEv4File(listFile);
            }
            return;
        }

        if(name.endsWith(".ev4")){
            file.delete();
        }

        simpleFileName(file);
    }

    private static void simpleFileName(File file1) {
        String name = file1.getName();
        if(name.endsWith(".mp4")){
            System.out.println(name);
            String renameTo = name.replace("_recv_", "");
            if(renameTo.startsWith("201")){
                renameTo = renameTo.substring(11);
                int i = renameTo.indexOf('、');
                if(i>=0) {
                    renameTo = renameTo.substring(0, i) + "." + renameTo.substring(i + 1);
                }
            }
            String absolutePath = file1.getParent();
            renameTo=absolutePath+File.separator+renameTo;
            System.out.println(renameTo);
            file1.renameTo(new File(renameTo));

        }
    }
}
