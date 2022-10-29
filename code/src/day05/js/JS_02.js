
//点击IMG事件 切换图片的操作
var flag = false;

function clickIMG() {
    var light = document.getElementById("img_light");
    if(flag){
        light.src = "img/picc.jpg";
        flag = false;
    } else {
        light.src = "img/picc_2.jpg";
        flag = true;
    }
}