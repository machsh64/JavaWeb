package com.ren.myssm.util;

import com.ren.web20.pojo.Topic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @program: WebCode
 * @author: Ren
 * @create: 2022-11-22 21:04
 * @description:  为有关Topic的功能的工具类
 **/
public class TopicUtil {

    public static List<Topic> getTopicTimeWCount(List<Topic> topicList){
        int i=0;
        for (Topic topic : topicList) {
            DateTimeFormatter dtfYear = DateTimeFormatter.ofPattern("yyyy");
            DateTimeFormatter dtfMonth = DateTimeFormatter.ofPattern("MM");
            DateTimeFormatter dtfDay = DateTimeFormatter.ofPattern("dd");

            LocalDateTime topicDateTime = topic.getTopicDateTime();
            String topicDate = dtfMonth.format(topicDateTime)+"月"+dtfDay.format(topicDateTime)+"日   " + dtfYear.format(topicDateTime);
            topic.setTopicTime(topicDate);
            topic.setCount(i++);
        }
        return topicList;
    }
}
