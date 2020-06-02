package com.xxc.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 和事务管理相关的工具类，它包含了，开启事务，提交事务，回滚事务和释放连接
 */
@Component("txManager")
@Aspect//声明当前是一个切面类
public class TransactionManager {
    @Autowired
    private com.xxc.utils.ConnectionUtils connectionUtils;

    public void setConnectionUtils(com.xxc.utils.ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /**
     * 开启事务
     */
  //  @Before("execution(* com.xxc.service.impl.*.*(..))")
    public  void beginTransaction(){
        System.out.println("开启事务");
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    //@AfterReturning("execution(* com.xxc.service.impl.*.*(..))")
    public  void commit(){
        try {
            connectionUtils.getThreadConnection().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    //@AfterThrowing("execution(* com.xxc.service.impl.*.*(..))")
    public  void rollback(){
        System.out.println("异常通知");
        try {
            connectionUtils.getThreadConnection().rollback();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 释放连接
     */
    //@After("execution(* com.xxc.service.impl.*.*(..))")
    public  void release(){
        try {
            connectionUtils.getThreadConnection().close();//还回连接池中
            connectionUtils.removeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Pointcut("execution(* com.xxc.service.impl.*.*(..))")
    public void pt1(){//切入点
    }
    @Around("pt1()")
   public Object transactionAround(ProceedingJoinPoint proceedingJoinPoint){
         //定义返回值
       Object rtValue = null;
       //获取需增强方法的参数
       Object[] args =proceedingJoinPoint.getArgs();
       //前置通知
       beginTransaction();
       //执行被拦截的方法
       try {
          rtValue= proceedingJoinPoint.proceed(args);
           //后置通知
           commit();
       } catch (Throwable throwable) {
           //异常通知
           rollback();
           throwable.printStackTrace();
       }finally {
           //最终通知
           release();
       }
    return  rtValue;
   }
}
