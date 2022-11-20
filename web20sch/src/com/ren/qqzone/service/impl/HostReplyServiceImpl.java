package com.ren.qqzone.service.impl;

import com.ren.qqzone.dao.HostReplyDAO;
import com.ren.qqzone.pojo.HostReply;
import com.ren.qqzone.pojo.UserBasic;
import com.ren.qqzone.service.HostReplyService;
import com.ren.qqzone.service.UserBasicService;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-17 21:32
 * @description:
 **/
public class HostReplyServiceImpl implements HostReplyService {

    private HostReplyDAO hostReplyDAO;
    private UserBasicService userBasicService;

    @Override
    public HostReply getHostReplyByReplyId(int replyId) {
        HostReply hostReply = hostReplyDAO.getHostReplyById(replyId);
        if (hostReply!=null) {
            UserBasic userBasic = userBasicService.getUserBasicByID(hostReply.getAuthorId());
            hostReply.setAuthor(userBasic);
        }
        return hostReply;
    }
}
