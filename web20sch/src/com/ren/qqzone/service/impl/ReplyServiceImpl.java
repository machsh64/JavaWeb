package com.ren.qqzone.service.impl;

import com.ren.qqzone.dao.ReplyDAO;
import com.ren.qqzone.pojo.HostReply;
import com.ren.qqzone.pojo.Reply;
import com.ren.qqzone.pojo.UserBasic;
import com.ren.qqzone.service.HostReplyService;
import com.ren.qqzone.service.ReplyService;
import com.ren.qqzone.service.UserBasicService;

import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-17 21:07
 * @description:
 **/
public class ReplyServiceImpl implements ReplyService {

    private ReplyDAO replyDAO;
    //此处引入的是其他pojo对应的Service接口，而不是DAO接口
    //其他pojo对应的业务逻辑是封装在service层的，调用别的业务的逻辑方法，而不要去深入考虑内部的细节
    private HostReplyService hostReplyService;
    private UserBasicService userBasicService;

    @Override
    public List<Reply> getReplyList(int topicId) {
        List<Reply> replyList = replyDAO.getReplyList(topicId);
        for (Reply reply : replyList) {
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            UserBasic userBasic = userBasicService.getUserBasicByID(reply.getAuthorId());
            reply.setAuthor(userBasic);
            reply.setHostReply(hostReply);
        }
        return replyList;
    }

}
