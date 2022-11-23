package com.ren.web20.dao.impl;

import com.ren.myssm.basedao.BaseDAO;
import com.ren.web20.dao.TopicDAO;
import com.ren.web20.pojo.Topic;

import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-19 10:44
 * @description:
 **/
public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {

    @Override
    public List<Topic> getTopicList(int authorId) {
        String sql = "SELECT id,title,headLine,content,topicDateTime,publish,author authorId " +
                "FROM w_topic " +
                "WHERE author=?";
        return executeQueryList(sql, authorId);
    }

    @Override
    public int delAuthorTopicList(int authorId) {
        String sql = "DELETE FROM w_topic " +
                "WHERE author=?";
        return executeUpdate(sql,authorId);
    }

    @Override
    public List<Topic> getAllTopic() {
        String sql = "SELECT id,title,headLine,content,topicDateTime,publish,author authorId " +
                "FROM w_topic";
        return executeQueryList(sql);
    }

    @Override
    public Topic getTopicById(int topicId) {
        String sql = "SELECT id,title,headLine,content,topicDateTime,publish,author authorId " +
                "FROM w_topic " +
                "WHERE id=?";
        return executeQuery(sql, topicId);
    }

    @Override
    public int delTopicById(int topicId) {
        String sql = "DELETE FROM w_topic " +
                "WHERE id=?";
        return executeUpdate(sql, topicId);
    }

    @Override
    public int updateTopic(Topic topic) {
        String sql = "UPDATE w_topic " +
                "SET title=?,headLine=?,content=?,topicDateTime=?,publish=? " +
                "WHERE id=?";
        return executeUpdate(sql, topic.getTitle(), topic.getHeadLine(), topic.getContent(), topic.getTopicDateTime(), topic.getPublish(), topic.getId());
    }


    @Override
    public int addTopic(Topic topic) {
        String sql = "INSERT INTO w_topic(title,headLine,content,topicDateTime,publish,author) " +
                "VALUES(?,?,?,?,?,?)";
        return executeUpdate(sql, topic.getTitle(), topic.getHeadLine(), topic.getContent(), topic.getTopicDateTime(), topic.getPublish(), topic.getAuthorId());
    }
}
