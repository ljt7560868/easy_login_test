<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>统一认证中心</title>

    <#import "common/common.macro.ftl" as netCommon>
    <@netCommon.commonStyle />
    <link rel="stylesheet" href="/sso/static/adminlte/plugins/iCheck/square/blue.css">

</head>
<body class="hold-transition login-page">

    <div class="login-box">
        <div class="login-logo">
            <a><b>HC</b>chatGPT</a>
        </div>
        <form action="/sso/auth/login" method="post">
            <div class="login-box-body">
                <p class="login-box-msg">统一认证中心</p>
                <div class="form-group has-feedback">
                    <input type="text" name="username" class="form-control" placeholder="Please input username." value="" maxlength="50" >
                    <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                </div>
                <div class="form-group has-feedback">
                    <input type="password" name="password" class="form-control" placeholder="Please input password." value="" maxlength="50" >
                    <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                </div>

                <#if errorMsg?exists>
                    <p style="color: red;">${errorMsg}</p>
                </#if>

                <div class="row">
                    <div class="col-xs-4">
                        <button type="submit" class="btn btn-primary btn-block btn-flat">Login</button>
                    </div>
                </div>
            </div>
        </form>
    </div>

</body>
<@netCommon.commonScript />
<script src="/sso/static/adminlte/plugins/iCheck/icheck.min.js"></script>
<script src="/sso/static/js/login.1.js"></script>
</html>