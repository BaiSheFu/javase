package com.xxc.proxy.jdkproxy;

/*此处我们使用的是一个演员的例子：
        在很久以前，演员和剧组都是直接见面联系的。没有中间人环节。
        而随着时间的推移，产生了一个新兴职业：经纪人（中间人），这个时候剧组再想找演员就需要通过经纪
        人来找了。下面我们就用代码演示出来。*/

/**
 * 一个经纪公司的要求:
 * 能做基本的表演和危险的表演
 */
public interface IActor {
    /**
     * 基本演出
     *
     * @param money
     */
    public void basicAct(float money);

    /**
     * 危险演出
     *
     * @param money
     */
    public void dangerAct(float money);
}

