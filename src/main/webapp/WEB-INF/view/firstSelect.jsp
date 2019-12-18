<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang=\"en\">
<head>
    <title>Select goods</title>
    <style type="text/css">
        #greetingStyle {
            margin-left: 10%;
            margin-right: 10%;
            background: #fc0;
            padding: 10px;
            text-align: center;
        }
    </style>
    <style type="text/css">
        #formStyle {
            margin-left: 10%;
            margin-right: 10%;
            background: #01DF01;
            padding: 10px;
            text-align: center;
        }
    </style>
</head>
<body>
<div id="greetingStyle">
    <c:set var="user" scope="session" value="${name}"/>
    <h3>Hello ${user}!</h3>
</div>
<div id="formStyle">
    <p>Make your order</p>
    <form action="goodsAddServlet" method="post">
        <p><label>
            <select name="good">
                <c:set var="allGoodsMap" scope="session" value="${allGoodsList}"/>
                <c:forEach items="${allGoodsMap}" var="entry">
                    <option>${entry['key']} (${entry['value']} $)</option>
                    <br/>
                </c:forEach>
            </select>
        </label></p>
        <p>
            <button type="submit" name="button" value="add">Add Item</button>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
            &nbsp;
            <button type="submit" name="button" value="submit">Submit</button>
        </p>
    </form>
</div>
</body>
</html>
