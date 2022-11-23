package com.ren.myssm.util;

import com.ren.web20.pojo.Topic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
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
            SimpleDateFormat yearSdf = new SimpleDateFormat("yyyy");
            SimpleDateFormat monthSdf = new SimpleDateFormat("MM");
            SimpleDateFormat daySdf = new SimpleDateFormat("dd");


            Date topicDateTime = topic.getTopicDateTime();
            String year = yearSdf.format(topicDateTime);
            String month = monthSdf.format(topicDateTime);
            String day = daySdf.format(topicDateTime);

            String topicDate = month +"月"+day + "日   " + year;
            topic.setTopicTime(topicDate);
            topic.setCount(i++);
        }
        return topicList;
    }
    public static Topic getTopicTimeWPo(Topic topic){

        SimpleDateFormat yearSdf = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthSdf = new SimpleDateFormat("MM");
        SimpleDateFormat daySdf = new SimpleDateFormat("dd");


        Date topicDateTime = topic.getTopicDateTime();
        String year = yearSdf.format(topicDateTime);
        String month = monthSdf.format(topicDateTime);
        String day = daySdf.format(topicDateTime);

        String topicDate = month +"月"+day + "日   " + year;
        topic.setTopicTime(topicDate);

        return topic;
    }
}
