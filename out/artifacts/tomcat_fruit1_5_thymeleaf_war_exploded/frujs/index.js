function delFruit(fid) {
    if(confirm('是否确认删除?')){
        window.location.href = 'delete.do?fid='+fid;
    }
}

function pageTr(pageNo) {
    window.location.href = 'index?pageNo='+pageNo;
}
