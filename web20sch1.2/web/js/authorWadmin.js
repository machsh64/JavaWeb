function delTopic(topicId){
    if(confirm("是否确认删除？")) {
        window.location.href = "topic.do?operate=delTopic&topicId=" + topicId;
    }
}
function delAuthor(authorId) {
    if (confirm("是否确认删除用户?")){
        window.location.href="user.do?operate=delAuthor&authorId="+authorId;
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
/*日期-时间选择-js*/
$(".contact-date").datetimepicker({
    autoclose: true,   //选择后自动关闭当前时间控件
    isRTL: Metronic.isRTL(), //RTL：right to left（从右向左显示），默认为false，即：从左向右显示
    format: "yyyy-mm-dd", //时间格式
    pickerPosition: (Metronic.isRTL() ? "bottom-right" : "bottom-left")//控件显示位置
});
