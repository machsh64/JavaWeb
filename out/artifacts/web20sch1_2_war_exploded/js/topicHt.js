
var count;
function tpMouseOn(count) {
    count = this.count;
    var tp_headLine = document.getElementsByClassName("tp_headLine")[count];
       tp_headLine.style.color="white";
}
function tpMouseLeave() {
    var tp_headLine = document.getElementsByClassName("tp_headLine")[count];
        tp_headLine.style.color="#3a3a3a";
}