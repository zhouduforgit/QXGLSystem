<%@ page pageEncoding="utf-8" %>
<html>
    <head>
        <style>
            .userAddBox{
                width:400px;
                background: #cfc;
                border:4px solid #ccc ;
                border-radius: 10px;
                box-shadow: 5px 5px 10px #000;
                margin:50px auto ;  /*外边距，盒子与盒子之间的距离*/

                padding-bottom:10px; /*内边距， 盒子内容与盒子之间的距离*/
            }

            .userAddBox input{
                width:250px;
                height:30px;
            }
            .userAddBox select{
                width:250px;
                height:30px;
            }

            .userAddBox tr{
                height:45px;
            }

            .userAddBox button{
                width:50px;
                height:30px;
            }
        </style>
        <script>
            function check(){ //function 表示是一个方法(函数)
                //获得密码和确认密码，比较
                //js中的变量是弱类型变量，定义变量时不能确定变量存储数据的类型，用var定义
                //java 用 int i ;  js 用 var i ;
                var upass = document.getElementById('upass');
                var repass = document.getElementById('repass') ;
                if(upass.value === repass.value){
                    return true ;//继续提交
                }

                alert('两次密码不一致');
                return false ;//终止提交
            }
        </script>
    </head>
    <body>
        <div class="userAddBox">
            <h2 align="center">新建用户</h2>
            <form action="UserAction.do?method=add" method="post" onsubmit="return check()">
                <table align="center">
                    <tr>
                        <td>用户名：</td>
                        <td><input name="uname" type="text" required="required" /></td>
                    </tr>

                    <tr>
                        <td>密码：</td>
                        <td><input name="upass" id="upass" type="password"  required="required" /></td>
                    </tr>

                    <tr>
                        <td>确认密码：</td>
                        <td><input type="password" id="repass"  required="required"  /></td>
                    </tr>

                    <tr>
                        <td>真实姓名：</td>
                        <td><input name="truename" type="text"  required="required"  /></td>
                    </tr>

                    <tr>
                        <td>年龄：</td>
                        <td><input name="age" type="number"  required="required"  /></td>
                    </tr>


                    <tr>
                        <td>性别：</td>
                        <td>
                            <select name="sex">
                                <option>男</option>
                                <option>女</option>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td colspan="2" align="center">
                            <button>保存</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>