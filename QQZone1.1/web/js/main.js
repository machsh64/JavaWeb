function delTopic(topicId) {
    if (confirm('是否确认删除？')){
        window.location.href="topic.do?topicId="+topicId+"&operate=delete";
    }
}