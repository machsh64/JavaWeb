package com.ren.web20.pojo;

import java.util.Date;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-19 10:43
 * @description:
 **/
public class Topic {
    private int id;
    private String title;
    private String headLine;
    private String content;
    private Date topicDateTime;
    private String topicTime;
    private String publish;  //出版社
    private int count;  //topic的行数
    private int authorId;
    private Author author;

    public Topic(){}

    /* 用于更新专题的构造器 */
    public Topic(int id,String title, String headLine, String content, Date topicDateTime,String publish, int authorId) {
        this.id = id;
        this.title = title;
        this.headLine = headLine;
        this.content = content;
        this.topicDateTime = topicDateTime;
        this.publish = publish;
        this.authorId = authorId;
    }

    /* 用于创建新专题的构造器 */
    public Topic(String title, String headLine, String content, Date topicDateTime,String publish, int authorId) {
        this.title = title;
        this.headLine = headLine;
        this.content = content;
        this.topicDateTime = topicDateTime;
        this.publish = publish;
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

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTopicDateTime() {
        return topicDateTime;
    }

    public void setTopicDateTime(Date topicDateTime) {
        this.topicDateTime = topicDateTime;
    }

    public String getTopicTime() {
        return topicTime;
    }

    public void setTopicTime(String topicTime) {
        this.topicTime = topicTime;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
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
                ", headLine='"+headLine + '\'' +
                ", content='" + content + '\'' +
                ", topicDateTime=" + topicDateTime +
                ", authorId=" + authorId +
                '}';
    }
}
