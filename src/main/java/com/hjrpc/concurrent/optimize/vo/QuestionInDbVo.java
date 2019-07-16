package com.hjrpc.concurrent.optimize.vo;

public class QuestionInDbVo {

    private final int id;
    private final String detail;
    private final String sha;

    public QuestionInDbVo(int id, String detail, String sha) {
        this.id = id;
        this.detail = detail;
        this.sha = sha;
    }

    public int getId() {
        return id;
    }

    public String getDetail() {
        return detail;
    }

    public String getSha() {
        return sha;
    }

    @Override
    public String toString() {
        return "QuestionInDbVo{" +
                "id=" + id +
                ", detail='" + detail + '\'' +
                ", sha='" + sha + '\'' +
                '}';
    }
}
