package com.atguigu.qqzone.pojo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-10 19:35
 * @description:
 **/
public class Topic {
    private int id;
    private String title;
    private String Content;
    private LocalDateTime topicDate;
    private UserBasic author;  //日志  M:1  多个用户对应一个日志

    private List<Reply> replyList;  //1:N  一个日志可以拥有多个回复


    public Topic(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public LocalDateTime getTopicDate() {
        return topicDate;
    }

    public void setTopicDate(LocalDateTime topicDate) {
        this.topicDate = topicDate;
    }

    public UserBasic getAuthor() {
        return author;
    }

    public void setAuthor(UserBasic author) {
        this.author = author;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", Content='" + Content + '\'' +
                ", topicDate=" + topicDate +
                '}';
    }
}
