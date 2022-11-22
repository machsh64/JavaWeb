function delTopic(topicId,logNum){
    if(confirm("是否确认删除？")) {
        window.location.href = "topic.do?operate=delTopic&topicId=" + topicId;
    }
}
function crash() {
    var title = document.getElementById("contact-title").value;
    var headLine = document.getElementById("contact-headLine").value;
    var content = document.getElementById("contact-content").value;
    var publish = document.getElementById("publish").value;

    if (isFull(title) && isFull(headLine && isFull(content)) && isFull(publish)) {
        alert("提交成功！");
    }else {
        alert("提交失败！");
    }
}

/* 判断字符是否不为空*/
function isFull(val) {
    var str = val.replace(/(^\s*)|(\s*$)/g, '');//去除空格;

    if (str == '' || str == undefined || str == null) {
        return false;
    } else {
        return true;
    }
}