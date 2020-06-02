package com.xxc.practice.juc;

import java.rmi.dgc.Lease;

class Resource {
   static int number=1;
    synchronized void  printA()  {
        while (number!=1){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (number==1){
            System.out.println("A");
                number++;
                this.notifyAll();
        }

    }
    synchronized void  printB(){
        while (number!=2){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (number==2) {
            System.out.println("B");
            number++;
           this.notifyAll();
        }
    }
    synchronized void  printC(){
        while (number!=3){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (number==3) {
            System.out.println("C");
            number=1;
            this.notifyAll();
        }
    }
}


public class ThreadTest {
    public static void main(String[] args) {
        Resource resource =new Resource();

            new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    resource.printA();
                }
            },"--A--").start();


            new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 10; i++) {
                    resource.printB();
                }
            },"--B--").start();


            new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    resource.printC();
                }
            },"--c--").start();

    }
}
