<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false"%>

<%@ page import="java.util.List" %>
<%@ page import="spring_department.mvc.models.Department" %>
<%--<%@ page session="false" pageEncoding="UTF-8" %>--%>
<%----%>


<html>
<head>
    <title>Enterprise</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue w3-opacity w3-right-align">
    <h1>Enterprise</h1>
</div>



<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">

        <div class="w3-container w3-blue w3-right-align">
            <h2></h2>

            <div class="w3-container w3-white w3-opacity w3-right-align w3-padding">

                <button class="w3-btn w3-hover-blue w3-round-large" onclick="location.href='/employees'">Employees</button>
            </div>
        </div>
    </div>
</div>
<TABLE  width="100%"  align="center" class="w3-white">

    <TD class="w3-hover-text-light-green" hspace="20" height="350" width="420"><img class="img" src="data:image/jpeg;base64,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"
                                                                                    hspace="20" height="350" width="420" alt="foto"></TD>
    <TD><H4><p>
        Доброго времени суток , уважаемый посетитель этой странички!
    </p>

        <p> Предполагается , что здесь будет всякоразная белиберда о том, какая замечательная данная организация, как выгодно она отличается от многочисленных конкурентов,каие прекрасные , нужные товары или услуги она производит, с какой , поистине отеческой заботой относятся к сотрудникам, и прочая. и прочая...
        </p>
        <p> Этот текст вообще не принципиален, напишем то, что Вам захочется. Вусь функционал данной странички - начать кнопку "Departments", но страница с одной кнопкой выглядит глуповато, поэтому и нужно вступление, которое никто не читает
        </p>
        <p>Поэтому давайте пропустим этот текст и нажмем на любую из двух кнопочек "Departments". Они абсолютно идентичны по функционалу, так как привязаны к одной странице.

        </p>
    </H4></TD>

    </tr>
</TABLE>
<TABLE  width="97%"  align="center" class="w3-white">
    <TD height="90"><H3>Предполагается, что на фото будет Ваша корпорация, или кроссовки, или фото любимой собаки, да чего Вашей душе угодно
        Фото тоже можно поменять или вообще убрать.

    </H3></TD>
</TABLE>

<div class="w3-container w3-blue w3-opacity w3-right-align">

</div>
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">

        <div class="w3-container w3-center w3-margin-bottom w3-padding">
            <div class="w3-card-4">

                <div class="w3-container w3-blue w3-right-align">
                    <h2></h2>



                    <div class="w3-container w3-white w3-opacity w3-right-align w3-padding">


                        <button class="w3-btn w3-hover-blue w3-round-large" onclick="location.href='/departments'"> Departments</button>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
