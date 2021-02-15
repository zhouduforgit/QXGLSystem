
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>登录</title>
    <style>
      .loginBox{
        width:400px;
        background:#cfc ;
        border:4px solid #ccc ;
        border-radius: 10px;
        margin:100px auto ; /*窗口剧中*/
      }

      .loginBox input{
        width:250px;
        height:30px;
      }

      .loginBox tr{
        height:45px;
      }

      .loginBox button{
        width:60px;
        height:30px;
      }
    </style>
  </head>
  <!--
    get 方式 请求速度快，传递数据量有限，参数会显示的跟在url的后面不安全，有tomcat处理中文
    post 方式 请求速度略慢，传递数据量无限，参数隐式传递比较安全，由编码处理中文
  -->
  <body>
    <div class="loginBox">
      <h2 align="center">用户登录</h2>
      <p align="center" style="color:red;font-size:14px;height:14px;">
        ${param.status==9?'用户名密码错误':''}
      </p>
      <form action="UserAction.do?method=login" method="post">
        <table align="center">
          <tr>
            <td>用户名：</td>
            <td><input name="uname" type="text" /></td>
          </tr>
          <tr>
            <td>密码：</td>
            <td><input name="upass" type="password" /></td>
          </tr>
          <tr>
            <td colspan="2" align="center"><button>登录</button></td>
          </tr>
        </table>
      </form>
    </div>
  </body>
</html>
