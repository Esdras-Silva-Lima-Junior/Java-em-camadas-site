<%@page import="model.Funcionario"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de Consulta</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300&display=swap" rel="stylesheet">
        <style>
            *{
                text-align: center;
                font-family: 'Roboto', sans-serif;
            }

            table{
                margin: 50px auto;
                text-align: center;
                width: 80%;
                border-collapse: collapse;
            }

            th,
            td{
                border: 1px solid black;
                background-color: gray;
            }

            table, th, td, input{
                padding: 10px
            }

            .tabela table th{
                border: 1px solid black;
                background-color: darkgray;
            }

            table tr td.excluir{
                background: darkgray;
                color: black;
            }

            table th.excluir,
            td.excluir{
                border: none;
            }

            h1{
                margin-top: 50px
            }

            input{
                cursor: pointer;
            }
        </style>
    </head>
    <body>

        <h2>Funcionario Encontrados:</h2>

        <%
            // Recebendo a lista e convertendo para list.
            List<Funcionario> consultaFuncionario = (List<Funcionario>) request.getAttribute("funcionariosEncontrados");
        %>

        <table>
            <tr>
            <strong>
                <th>MATRICULA</th>
                <th>NOME</th>
                <th>EMAIL</th>
                <th>TELEFONE</th>
                <th>CPF</th>
                <th>CARGO</th>
            </strong>
        </tr>
        <%
            for (Funcionario funcionario : consultaFuncionario) {
        %>
        <tr>
            <td><%= funcionario.getMatricula()%></td>
            <td><%= funcionario.getNome()%></td>
            <td><%= funcionario.getEmail()%></td>
            <td><%= funcionario.getTelefone()%></td>
            <td><%= funcionario.getCpf()%></td>
            <td><%= funcionario.getCargo()%></td>
            <td class="excluir">
                <form method="post" action="ControleFuncionario?flag=excluir">
                    <input type="hidden" name="matricula" value="<%= funcionario.getMatricula()%>">
                    <input type="submit" value="Apagar">
                </form>
            </td>
        </tr>

        <%
            }
        %>
    </table>
</body>
</html>
