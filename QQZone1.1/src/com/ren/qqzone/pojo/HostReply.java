package com.ren.qqzone.pojo;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-10 19:35
 * @description:
 **/
public class HostReply {
    private int id;
    private String content;
    private LocalDateTime hostReplyDate;
    private int authorId;
    private int replyId;
    private UserBasic author;     // M:1  多个主人回复可对应一个作者
    private Reply reply;  //1:1  一个主人回复可对应一个回复

    public HostReply(){

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

    public LocalDateTime getHostReplyDate() {
        return hostReplyDate;
    }

    public void setHostReplyDate(LocalDateTime hostReplyDate) {
        this.hostReplyDate = hostReplyDate;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public UserBasic getAuthor() {
        return author;
    }

    public void setAuthor(UserBasic author) {
        this.author = author;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }

    @Override
    public String toString() {
        return "HostReply{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", hostReplyDate=" + hostReplyDate +
                ", authorId=" + authorId +
                ", replyId=" + replyId +
                '}';
    }
}
