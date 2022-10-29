//设置系统加载完页面后进行的操作
window.onload = function () {
    var table = document.getElementById("div_table");
    var tres = table.rows;
    var priceAll = 0;

    //显示蓝色表格背景
    for (var i = 1; i < tres.length - 1; i++) {
        mouseOver(tres[i]);
    }
    //单价单元格的手势修改  总价计算
    for (var i = 1; i < tres.length; i++) {
        priceAll += parseInt(tres[i].cells[3].innerText);
        tres[4].cells[1].innerHTML = priceAll;
        //单价的操作
        handsOn(tres[i].cells[2]);
        //数量的修改
        handsOnNum(tres[i].cells[1]);
    }
}

/*鼠标移动到表格上的操作*/
var mouseOver = function (tre) {
    //覆盖在表格上
    tre.onmouseover = function () {
        tre.style.backgroundColor = "blue";
        for (var i = 0; i < tre.cells.length - 1; i++) {
            tre.cells[i].style.color = "white";
        }
        tre.cells[4].childNodes[0].style.color = "white";
    }
    //移除表格时
    tre.onmouseout = function () {
        tre.style.backgroundColor = "transparent";
        for (var i = 0; i < tre.cells.length - 1; i++) {
            tre.cells[i].style.color = "black";
        }
        tre.cells[4].childNodes[0].style.color = "blue";
    }
}

//单价单元格的事件
var handsOn = function (tde) {
    //悬浮在单价单元格上的操作
    tde.onmouseover = function () {
        /*设置光标为手的操作*/
        tde.style.cursor = "pointer";
    }
    //单击单元格进行修改事件
    tde.onclick = function () {
        if (!isNaN(tde.innerHTML)) {
            tde.innerHTML = "<input type='text' placeholder='" + parseInt(tde.innerHTML) + "' onblur = 'onblurRow2(this)' size='4'>";
        }
    }
}

//数量单元格的事件
var handsOnNum = function (tde) {
    //单击单元格进行修改事件
    tde.onclick = function () {
        if (!isNaN(tde.innerHTML)) {
            tde.innerHTML = "<input type='text' placeholder='" + parseInt(tde.innerHTML) + "' onblur = 'onblurRow2(this)' size='4'>";
        }
    }
}

//设置失去焦点后，将框变为文本
function onblurRow2(td_input) {
    td_input.parentNode.innerHTML = td_input.value;
    //进行价格更新
    price_once();
}

//水果价格的计算
function price_once() {
    var table = document.getElementById("div_table");
    var tres = table.rows;
    var priceAll = document.getElementById("td_price");
    var allPrice = 0;

    //对价格的更新
    for (var i = 1; i < tres.length; i++) {
        //单个水果总价的计算
        tres[i].cells[3].innerHTML = parseInt(tres[i].cells[1].innerText) * parseInt(tres[i].cells[2].innerText);
        allPrice += parseInt(tres[i].cells[3].innerText);
        //对所有总价格的更新
        priceAll.innerHTML = allPrice;
    }
}

//增加水果种类的事件
var submit = document.getElementById("submit");
submit.onclick = function () {
    var name = document.getElementsByName("name")[0].value;
    var number = document.getElementsByName("number")[0].value;
    var price = document.getElementsByName("price")[0].value;
    var table_tr = document.getElementById("div_table");

    //获取价格的行
    var price_tr = document.getElementById("price_tr");
    var table = price_tr.parentNode;
    //删除总价格行
    table.removeChild(price_tr);
    //添加水果行与总价格行
    table_tr.innerHTML += "<tr>" +
        "<td>" + name + "</td>" +
        "<td>" + number + "</td>" +
        "<td>" + price + "</td>" +
        "<td>" + 0 + "</td>" +
        "<td><a href='javascript:void(0)' onclick = 'onclickM(this)'>删除</a></td>" +
        "</tr>" +
        "<tr id='price_tr'>" +
        "<td>总价</td>" +
        "<td colspan='4' id='td_price'>0</td>" +
        "</tr>";
    price_once();
}

function onclickM(obj) {
    var tr = obj.parentNode.parentNode;
    var table = tr.parentNode;

    table.removeChild(tr);
    price_once();
}
