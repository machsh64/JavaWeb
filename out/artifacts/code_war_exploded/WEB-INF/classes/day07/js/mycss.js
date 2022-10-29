
var click_a = document.getElementsByClassName("div_a")[0];

var flag = false;
 var click =  click_a.onclick = function () {
    flag = true;
}

var mouseOn = click_a.onmouseover = function () {
    click_a.className = "div_a_mouseOn";
}

var mouseOut = click_a.onmouseout = function () {
    if(flag) {
        click_a.className = "div_a_after";
    }else {
        click_a.className = "div_a";
    }
}