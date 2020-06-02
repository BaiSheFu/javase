package com.xxc.proxy.cglibpoxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Client {
    public static void main(String[] args) {
       final Actor actor =new Actor();

        /* * 基于子类的动态代理
         *  要求：
         *   被代理对象不能是最终类
         *  用到的类：
         *   Enhancer
         *  用到的方法：
         *   create(Class, Callback)
         *  方法的参数：
         *   Class：被代理对象的字节码
         *   Callback：如何代理*/

       Actor cglibProxyActor = (Actor) Enhancer.create(actor.getClass()
                , new MethodInterceptor() {
                    /**
                     * 执行被代理对象的任何方法，都会经过该方法。在此方法内部就可以对被代理对象的任何
                     方法进行增强。
                     *
                     * 参数：
                     *  前三个和基于接口的动态代理是一样的。
                     *  MethodProxy：当前执行方法的代理对象。
                     * 返回值：
                     *  当前执行方法的返回值
                     */
                    @Override
                    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
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
                });
       cglibProxyActor.basicAct(20000);
        cglibProxyActor.dangerAct(40000);

    }
}
