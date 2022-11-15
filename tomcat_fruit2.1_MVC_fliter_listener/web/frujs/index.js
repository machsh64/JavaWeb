function delFruit(fid) {
    if(confirm('是否确认删除?')){
        window.location.href = 'fruit.do?fid='+fid+'&operator=delete';
    }
}

function pageTr(pageNo) {
    window.location.href = 'fruit.do?pageNo='+pageNo+'&operator=index';
}
