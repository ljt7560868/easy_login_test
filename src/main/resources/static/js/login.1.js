$(function () {

    // input iCheck
    $('input').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%' // optional
    });
});

$("#btnLogin").click(function () {
    console.info('表单提交')
    const data = {};
    $($("#loginForm").serializeArray()).each(function () {
        data[this.name] = this.value;
    });
    console.info(data)
    $.ajax({
        type: "POST",   //提交的方法
        url: "/auth/login", //接口地址
        data: JSON.stringify(data),// 转化为json字符串
        async: false,//false同步，true异步
        dataType: "json",
        contentType: "application/json",
        error: function (request) {  //失败
            console.info("登陆失败："+data);
            alert("Connection error");
        },
        success: function (data) {  //成功
            console.info("登陆成功："+data); //就将返回的数据显示出来
            //设置cookie
            /*$.cookie('AccessToken', '7cp4VkZ3Nju1AOjy', { expires: 3600, path: '/', domain: 'http://127.0.0.1' });
            // 关闭当前页面
            window.close();
            // 打开新页面
            //window.open('http://hc.chatgpt.local');
            //window.open('http://www.baidu.com');
            // 打开新页面
            //var newWindow = window.open('http://hc.chatgpt.local');
            var newWindow = window.open('http://www.baidu.com');
            // 等待新页面加载完成
            $(newWindow).on('load', function() {
                // 在新页面中设置 cookie
                newWindow.document.cookie = 'AccessToken=7cp4VkZ3Nju1AOjy; path=/;';
            });*/
        }
    });
})

function submitForm() {

}
