package com.atguigu.myssm.basedao;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-08 15:06
 * @description:
 **/
public class DAOException extends RuntimeException{

    static final long serialVersionUID = -7034897190745766939L;

    public DAOException(String msg){
        super(msg);
    }
}
