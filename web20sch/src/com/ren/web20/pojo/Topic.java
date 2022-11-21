package com.ren.web20.pojo;

import java.time.LocalDateTime;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-19 10:43
 * @description:
 **/
public class Topic {
    private int id;
    private String title;
    private String content;
    private LocalDateTime topicDateTime;
    private String topicImg;
    private int count;  //topic的行数
    private int authorId;
    private Author author;

    public Topic(){}

    /* 用于创建新专题的构造器 */
    public Topic(String title, String content, LocalDateTime topicDateTime, String topicImg, int authorId) {
        this.title = title;
        this.content = content;
        this.topicDateTime = topicDateTime;
        this.topicImg = topicImg;
        this.authorId = authorId;
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
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTopicDateTime() {
        return topicDateTime;
    }

    public void setTopicDateTime(LocalDateTime topicDateTime) {
        this.topicDateTime = topicDateTime;
    }

    public String getTopicImg() {
        return topicImg;
    }

    public void setTopicImg(String topicImg) {
        this.topicImg = topicImg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", topicDateTime=" + topicDateTime +
                ", topicImg='" + topicImg + '\'' +
                ", authorId=" + authorId +
                '}';
    }
}
