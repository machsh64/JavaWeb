function delTopic(topicId) {
    if (confirm('是否确认删除？')){
        window.location.href="user.do?topicId="+topicId+"&operate=delete";
    }
}