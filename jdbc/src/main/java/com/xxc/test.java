package com.xxc;

import java.util.Scanner;

public class test {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        String s =scanner.nextLine();
        reverseWords(s);
    }

    public static void reverseWords(String s){
        //对单词进行切割
        String[] inputWords = s.split(" ");
        StringBuilder sb =new StringBuilder();
        if(inputWords.length>0) {
            for (int i = inputWords.length - 1; i >= 0; i--) {
                if (null != inputWords[i] && inputWords[i].length() > 0) {//判断空字符
                    if (i == 0) {
                        sb.append(inputWords[i]);
                    } else {
                        sb.append(inputWords[i] + " ");
                    }
                }
            }
        }
        System.out.println(sb.toString());
    }
}
