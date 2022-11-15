package com.atguigu.myssm.mysringmvc;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-08 15:08
 * @description:
 **/
public class DispatcherServletException extends RuntimeException{
    static final long serialVersionUID = -7034897190745766939L;

    public DispatcherServletException(String msg){
        super(msg);
    }
}
