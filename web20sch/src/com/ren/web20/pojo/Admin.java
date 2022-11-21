package com.ren.web20.pojo;

import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-19 10:43
 * @description:
 **/
public class Admin {
    private int id;
    private String nickName;
    private String loginId;
    private String password;

    private List<Topic> topicList;
    private List<Author> authorList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
