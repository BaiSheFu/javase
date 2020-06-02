package com.xxc.utils;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 和事务管理相关的工具类，它包含了，开启事务，提交事务，回滚事务和释放连接
 */
public class TransactionManager {

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /**
     * 开启事务
     */
    public  void beginTransaction(){
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
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
    public  void release(){
        try {
            connectionUtils.getThreadConnection().close();//还回连接池中
            connectionUtils.removeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
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
