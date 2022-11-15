package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.dao.TopicDAO;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

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
        String sql = "SELECT id,title,content " +
                "FROM t_topic " +
                "WHERE author = ?";
        List<Topic> topicList = executeQueryList(sql, userBasic.getId());

        sql = "SELECT topicDate " +
                "FROM t_topic " +
                "WHERE author = ?";
        List<Object> topicDateObjList = getValues(sql,userBasic.getId());
        List<LocalDateTime> topicDate = new LinkedList<>();
        for (Object topicDateObj : topicDateObjList) {
            topicDate.add((LocalDateTime) topicDateObj);
        }

        for(int i = 0; i < topicList.size(); i++){
           topicList.get(i).setTopicDate(topicDate.get(i));
        }
        return topicList;
    }

    @Override
    public int addTopic(Topic topic) {
        String sql = "INSERT INTO t_topic(title,content,topicDate,author) " +
                "VALUES(?,?,?,?)";
        return executeUpdate(sql,topic.getTitle(),topic.getContent(),topic.getTopicDate(),topic.getAuthor());
    }

    @Override
    public int delTopic(Topic topic) {
        String sql = "DELETE FROM t_topic " +
                "WHERE id = ?";
        return executeUpdate(sql,topic.getId());
    }

    @Override
    public Topic getTopic(int id) {
        String sql = "SELECT id,title,content " +
                "FROM t_topic " +
                "WHERE id = ?";
        Topic topic = executeQuery(sql, id);
        sql = "SELECT topicDate " +
                "FROM t_topic " +
                "WHERE id = ?";
        Object topicDateObj = getValue(sql, id);
        LocalDateTime topicDate = (LocalDateTime) topicDateObj;
        topic.setTopicDate(topicDate);
        return topic;
    }
}
