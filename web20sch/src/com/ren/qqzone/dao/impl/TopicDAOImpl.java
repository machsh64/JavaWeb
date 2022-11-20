package com.ren.qqzone.dao.impl;

import com.ren.myssm.basedao.BaseDAO;
import com.ren.qqzone.dao.TopicDAO;
import com.ren.qqzone.pojo.Topic;
import com.ren.qqzone.pojo.UserBasic;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-11 16:15
 * @description:
 **/
public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        String sql = "SELECT id,title,content,topicDate,author authorId " +
                "FROM t_topic " +
                "WHERE author = ?";
        return executeQueryList(sql, userBasic.getId());
    }

    @Override
    public int addTopic(Topic topic) {
        String sql = "INSERT INTO t_topic(title,content,topicDate,author) " +
                "VALUES(?,?,?,?)";
        return executeUpdate(sql,topic.getTitle(),topic.getContent(),topic.getTopicDate(),topic.getAuthor().getId());
    }

    @Override
    public int delTopic(int topicId) {
        String sql = "DELETE FROM t_topic " +
                "WHERE id = ?";
        return executeUpdate(sql,topicId);
    }

    @Override
    public Topic getTopic(int id) {
        String sql = "SELECT id,title,content,topicDate,author authorId " +
                "FROM t_topic " +
                "WHERE id = ?";

        return executeQuery(sql, id);
    }
}
