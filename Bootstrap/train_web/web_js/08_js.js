//获取超链接的元素
var search_btn = document.getElementsByClassName("search_btn")[0];

//绑定超链接的点击 移动  点击后事件
var flag = false;
search_btn.onmouseover = function () {
    search_btn.className = "search_btn_mouseOn";
}

search_btn.onmouseout = function () {
    search_btn.className = "search_btn";
}

search_btn.onclick = function () {
    search_btn.className = "search_btn_mouseOn";
}
