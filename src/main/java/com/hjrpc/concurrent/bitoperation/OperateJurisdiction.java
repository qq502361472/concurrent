package com.hjrpc.concurrent.bitoperation;

public class OperateJurisdiction {

    //0001
    public static final int SELECT_JURISDICETION = 1;
    //0010
    public static final int INSERT_JURISDICETION = 1 << 1;
    //0100
    public static final int UPDATE_JURISDICETION = 1 << 2;
    //1000
    public static final int DELETE_JURISDICETION = 1 << 3;

    private static int state;

    public OperateJurisdiction(int state) {
        this.state = state;
    }

    public void setJurisdicetion(int pre) {
        state |= pre;
    }

    public boolean isAllow(int pre) {
        return (state & pre) == pre;
    }

    public boolean isNotAllow(int pre) {
        return (state & pre) == 0;
    }

    //1011
    //0011
    //1100  ~
    //
    public void remove(int pre){
        state=state&(~pre);
    }


    public static void main(String[] args) {

        OperateJurisdiction jurisdiction = new OperateJurisdiction(0);
        printJurisdiction(jurisdiction);
        jurisdiction.setJurisdicetion(SELECT_JURISDICETION);
        printJurisdiction(jurisdiction);
        jurisdiction.setJurisdicetion(SELECT_JURISDICETION);
        printJurisdiction(jurisdiction);
        jurisdiction.setJurisdicetion(INSERT_JURISDICETION | UPDATE_JURISDICETION);
        printJurisdiction(jurisdiction);
        jurisdiction.setJurisdicetion(INSERT_JURISDICETION | UPDATE_JURISDICETION);
        printJurisdiction(jurisdiction);
        jurisdiction.setJurisdicetion(DELETE_JURISDICETION);
        printJurisdiction(jurisdiction);



        System.out.println("-------------------------------");
        printJurisdiction(jurisdiction);
        jurisdiction.remove(SELECT_JURISDICETION);
        printJurisdiction(jurisdiction);
        jurisdiction.remove(SELECT_JURISDICETION);
        printJurisdiction(jurisdiction);
        jurisdiction.remove(INSERT_JURISDICETION | UPDATE_JURISDICETION);
        printJurisdiction(jurisdiction);
        jurisdiction.remove(INSERT_JURISDICETION | UPDATE_JURISDICETION);
        printJurisdiction(jurisdiction);
        jurisdiction.remove(DELETE_JURISDICETION);
        printJurisdiction(jurisdiction);
    }

    private static void printJurisdiction(OperateJurisdiction jurisdiction) {
        printYesJurisdiction(jurisdiction);
//        printNotJurisdiction(jurisdiction);
    }

    private static void printYesJurisdiction(OperateJurisdiction jurisdiction) {
        System.out.println(state + ":" + Integer.toBinaryString(state) + "|" + jurisdiction.isAllow(SELECT_JURISDICETION)
                + "|" + jurisdiction.isAllow(INSERT_JURISDICETION)
                + "|" + jurisdiction.isAllow(UPDATE_JURISDICETION)
                + "|" + jurisdiction.isAllow(DELETE_JURISDICETION));
    }

    private static void printNotJurisdiction(OperateJurisdiction jurisdiction) {
        System.out.println(state + ":" + Integer.toBinaryString(state) + "|" + jurisdiction.isNotAllow(SELECT_JURISDICETION)
                + "|" + jurisdiction.isNotAllow(INSERT_JURISDICETION)
                + "|" + jurisdiction.isNotAllow(UPDATE_JURISDICETION)
                + "|" + jurisdiction.isNotAllow(DELETE_JURISDICETION));
    }
}
