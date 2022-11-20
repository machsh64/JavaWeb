package com.ren.qqzone.service;

import com.ren.qqzone.pojo.Reply;

import java.util.List;

public interface ReplyService {
    //根据Topic的id获取所有关联的回复
    List<Reply> getReplyList(int topicId);
}
