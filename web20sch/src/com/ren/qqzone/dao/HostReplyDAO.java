package com.ren.qqzone.dao;

import com.ren.qqzone.pojo.HostReply;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-10 21:44
 * @description:
 **/
public interface HostReplyDAO {
    //根据回复的id获取主人回复
    HostReply getHostReplyById(int replyId);
}
