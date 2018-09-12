<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
<ul>
    <li><a href="/explain/test">说明页面</a></li>
    <li><a href="/test/list">用户查询</a></li>
</ul>

<h3>收货地址管理</h3>
<li>POST:<a href="/api/address/insert">/api/address/insert</a></li><br/>
描述：对数据进行增加
参数：name ,area,detailed,code,phone,user_id

<br/><br/> <li>POST:<a href="/api/address/deleteById">api/address/deleteById</a></li><br/>
描述：对数据进行删除
参数：id

<br/>  <li>GET:<a href="/api/address/list">/api/address/list</a></li><br/>
描述：对数据进行查

<br/>  <li>POST:<a href="/api/address/updateById">api/address/updateByIdt</a></li><br/>
描述：对数据进行更新
参数：id,name ,area,detailed,code,phone,user_id

<br/>  <li>POST:<a href="/api/address/selectByUserId">api/address/selectByUserId</a></li><br/>
描述：通过user_id查询个人收货地址
参数：user_id




</body>
</html>
