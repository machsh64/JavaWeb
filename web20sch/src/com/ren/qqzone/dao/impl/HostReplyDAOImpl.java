package com.ren.qqzone.dao.impl;

import com.ren.myssm.basedao.BaseDAO;
import com.ren.qqzone.dao.HostReplyDAO;
import com.ren.qqzone.pojo.HostReply;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-17 21:35
 * @description:
 **/
public class HostReplyDAOImpl extends BaseDAO<HostReply> implements HostReplyDAO {
    @Override
    public HostReply getHostReplyById(int replyId) {
        String sql = "SELECT id,content,hostReplyDate,author authorId,reply replyId " +
                "FROM t_host_reply " +
                "WHERE reply = ?";
        return executeQuery(sql,replyId);
    }
}
