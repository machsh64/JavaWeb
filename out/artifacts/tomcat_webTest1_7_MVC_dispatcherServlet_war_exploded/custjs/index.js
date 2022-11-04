function delCustomer(id) {
    if(confirm('是否确认删除?')){
        window.location.href = 'customer.do?id='+id+"&operator=delete";
    }
}
function pageTr(pageNo){
    window.location.href = 'customer.do?pageNo='+pageNo+"&operator=index";
}
