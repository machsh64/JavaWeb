package com.ren.qqzone.pojo;

import java.time.LocalDateTime;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-10 19:35
 * @description:
 **/
public class Reply {
    private int id;
    private String content;
    private LocalDateTime replyDate;
    private int authorId;
    private int topicId;   //N:1  多个回复可对应一个日志
    private UserBasic author;
    private Topic topic;   //N:1  多个回复可对应一个日志

    private HostReply hostReply;    //1:1  一个回复可对应一个主人回复

    public Reply(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(LocalDateTime replyDate) {
        this.replyDate = replyDate;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public UserBasic getAuthor() {
        return author;
    }

    public void setAuthor(UserBasic author) {
        this.author = author;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public HostReply getHostReply() {
        return hostReply;
    }

    public void setHostReply(HostReply hostReply) {
        this.hostReply = hostReply;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", replyDate=" + replyDate +
                ", author=" + authorId +
                ", topic=" + topicId +
                '}';
    }
}
