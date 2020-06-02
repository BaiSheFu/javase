package com.xxc.proxy.jdkproxy;

public class Actor implements IActor {
    @Override
    public void basicAct(float money) {
        System.out.println("拿钱  到手 "+money+"  干活 开始basicAct表演了---干完收工");
    }

    @Override
    public void dangerAct(float money) {
        System.out.println("拿钱 到手 "+money+" 干活 开始dangerAct表演了------干完收工");

    }
}
