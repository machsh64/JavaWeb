package com.ren.qqzone.pojo;

import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-10 19:34
 * @description:
 **/
public class UserBasic {
    private int id;  //id
    private String LoginId;   //登录账号
    private String nickName;  //账户名
    private String pwd;  //密码
    private String headImg;  //头像

    private UserDetail userDetail; //1:1  一个用户可以拥有一个信息
    private List<Topic> topicList;  //1:N  一个用户可以拥有多个日志
    private List<UserBasic> friendList;  //M:N  多个用户可对应多个朋友

    private Topic topic;

    public UserBasic() {
    }

    //仅获取用户列表的构造器
    public UserBasic(int id, String loginId, String nickName, String headImg) {
        this.id = id;
        LoginId = loginId;
        this.nickName = nickName;
        this.headImg = headImg;
    }

    //设置用户所有数据的构造器
    public UserBasic(int id, String loginId, String nickName, String pwd, String headImg) {
        this.id = id;
        LoginId = loginId;
        this.nickName = nickName;
        this.pwd = pwd;
        this.headImg = headImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginId() {
        return LoginId;
    }

    public void setLoginId(String loginId) {
        LoginId = loginId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public List<UserBasic> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<UserBasic> friendList) {
        this.friendList = friendList;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "UserBasic{" +
                "id=" + id +
                ", LoginId='" + LoginId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", headImg='" + headImg + '\'' +
                '}';
    }
}
