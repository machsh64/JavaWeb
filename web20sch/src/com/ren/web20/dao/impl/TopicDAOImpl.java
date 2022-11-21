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
        String sql = "SELECT id,title,content,topicDateTime,topicImg,author " +
                "FROM w_topic " +
                "WHERE author=?";
        return executeQueryList(sql,authorId);
    }

    @Override
    public List<Topic> getAllTopic() {
        String sql = "SELECT id,title,content,topicDateTime,topicImg,author " +
                "FROM w_topic";
        return executeQueryList(sql);
    }

    @Override
    public int addTopic(Topic topic) {
        String sql = "INSERT INTO w_topic(title,content,topicDateTime,topicImg,author) " +
                "VALUES(?,?,?,?,?)";
        return executeUpdate(sql,topic.getTitle(),topic.getContent(),topic.getTopicDateTime(),topic.getTopicImg(),topic.getAuthorId());
    }
}
