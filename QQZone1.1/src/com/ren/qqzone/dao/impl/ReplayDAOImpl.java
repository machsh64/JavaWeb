package com.ren.qqzone.dao.impl;

import com.ren.myssm.basedao.BaseDAO;
import com.ren.qqzone.dao.ReplyDAO;
import com.ren.qqzone.pojo.Reply;
import com.ren.qqzone.service.ReplyService;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-17 21:10
 * @description:
 **/
public class ReplayDAOImpl extends BaseDAO<Reply> implements ReplyDAO {
    @Override
    public List<Reply> getReplyList(int topicId) {
        String sql = "SELECT id,content,replyDate,author authorId,topic topicId " +
                "FROM t_reply " +
                "WHERE topic = ?";
        return executeQueryList(sql, topicId);
    }

    @Override
    public int addReply(Reply reply) {
        return 0;
    }

    @Override
    public int delReply(int id) {
        return 0;
    }
}
