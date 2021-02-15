<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        .userEditBox{
            width:400px;
            background: #cfc;
            border:4px solid #ccc ;
            border-radius: 10px;
            box-shadow: 5px 5px 10px #000;
            margin:50px auto ;  /*外边距，盒子与盒子之间的距离*/

            padding-bottom:10px; /*内边距， 盒子内容与盒子之间的距离*/
        }

        .userEditBox input{
            width:250px;
            height:30px;
        }
        .userEditBox select{
            width:250px;
            height:30px;
        }

        .userEditBox tr{
            height:45px;
        }

        .userEditBox button{
            width:50px;
            height:30px;
        }
    </style>
    <script>

    </script>
</head>
<body>
<div class="userEditBox">
    <h2 align="center">编辑用户</h2>
    <form action="UserAction.do?method=update" method="post" >
        <!-- param.uid == request.getParameter("uid")   ${requestScope.user.uid} request.getAttribute()
             如果能使用getParameter方法获得参数        如果使用getAttribute方法获得数据
             请求参数是如何传递呢？                    数据是如何传递呢？
             url?uid=1                              request.setAttribute("uid")
             在action中转发时，并没有使用?传递参数
             那是因为转发属于一次请求响应，与最初的编辑请求共用一个request
             点击编辑按钮，发送编辑请求时?传递了uid
        -->
        <input type="hidden" name="uid" value="${param.uid}">
        <table align="center">
            <tr>
                <td>用户名：</td>
                <td><input name="uname" type="text" required="required" value="${requestScope.user.uname}" /></td>
            </tr>

            <tr>
                <td>真实姓名：</td>
                <td><input name="truename" type="text"  required="required" value="${requestScope.user.truename}" /></td>
            </tr>

            <tr>
                <td>年龄：</td>
                <td><input name="age" type="number"  required="required" value="${requestScope.user.age}" /></td>
            </tr>

            <tr>
                <td>性别：</td>

                <td>
                    <select name="sex">
                        <c:if test="${requestScope.user.sex == '男'}">
                            <option selected="selected">男</option>
                            <option >女</option>
                        </c:if>
                        <c:if test="${requestScope.user.sex == '女'}">
                            <option >男</option>
                            <option selected="selected">女</option>
                        </c:if>
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