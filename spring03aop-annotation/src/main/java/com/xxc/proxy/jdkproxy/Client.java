package com.xxc.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {
    //客服  找一剧组要看表演
    public static void main(String[] args) {

        //行吧
        // 以前 剧组自己去找一个演员
//        Actor actor = new Actor();
//        actor.basicAct(1000f);
//        actor.dangerAct(5000f);

        //现在  剧组不想太麻烦去找人海捞人 就去找经济公司
        final Actor actor = new Actor();
        IActor proxyActor = (IActor) Proxy.newProxyInstance(actor.getClass().getClassLoader()
                , actor.getClass().getInterfaces()
                , new InvocationHandler() {
                    /**
                     * 执行被代理对象的任何方法，都会经过该方法。
                     * 此方法有拦截的功能。
                     *
                     * 参数：
                     *  proxy：代理对象的引用。不一定每次都用得到
                     *  method：当前执行的方法对象
                     *  args：执行方法所需的参数
                     * 返回值：
                     *  当前执行方法的返回值
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)  {
                        String methodName =method.getName();
                        Float money = (Float) args[0];
                        //返回值
                        Object resultValue=null;
                        if("basicAct".equals(methodName)){
                            System.out.println("进入基本表演方法basicAct");
                            if(money>2000){
                                try {
                                    resultValue = method.invoke(actor, money / 2);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }else {
                                System.out.println(" money too less no basicAct");
                            }
                        }
                        if("dangerAct".equals(methodName)){
                            //危险演出,没有 5000 不演
                            if(money > 5000){
                                try {
                                    //看上去剧组是给了 50000，实际到演员手里只有 25000
                                    //这就是我们没有修改原来 dangerAct 方法源码，对方法进行了增强
                                    resultValue = method.invoke(actor, money / 2);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }
                        return resultValue;
                    }
                }
        );
        proxyActor.basicAct(5000);
        proxyActor.dangerAct(6000);
        /**
         * 代理：
         *  间接。
         * 获取代理对象：
         *  要求：
         *   被代理类最少实现一个接口
         * 创建的方式
         *   Proxy.newProxyInstance(三个参数)
         * 参数含义：
         *  ClassLoader：和被代理对象使用相同的类加载器。
         *  Interfaces：和被代理对象具有相同的行为。实现相同的接口。
         *  InvocationHandler：如何代理。
         *    策略模式：使用场景是：
         *       数据有了，目的明确。
         *       如何达成目标，就是策略。
         *
         */

    }
}
