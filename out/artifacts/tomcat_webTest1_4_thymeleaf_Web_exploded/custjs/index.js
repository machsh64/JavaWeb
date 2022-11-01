function delCustomer(id) {
    if(confirm('是否确认删除?')){
        window.location.href = 'delete.do?id='+id;
    }
}

function pageTr(pageNo){
    window.location.href = 'index.do?pageNo='+pageNo;
}