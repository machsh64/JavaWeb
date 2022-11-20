package com.ren.qqzone.dao;

import com.ren.qqzone.pojo.Reply;
import com.ren.qqzone.pojo.Topic;

import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-10 21:42
 * @description:
 **/
public interface ReplyDAO {
    //获取指定日志的回复列表
    List<Reply> getReplyList(int topicId);
    //添加回复
    int addReply(Reply reply);
    //删除回复  根据id删除
    int delReply(int id);
}
