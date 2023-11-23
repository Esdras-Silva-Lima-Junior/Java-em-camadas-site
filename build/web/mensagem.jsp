<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300&display=swap" rel="stylesheet">
        <style>
            *{
                padding: 5px;
                color: black;
                font-family: 'Roboto', sans-serif;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <%= request.getAttribute("mensagem") %>
    </body>
</html>
