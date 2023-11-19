<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="altstyle.css"/>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300&display=swap" rel="stylesheet">
    </head>
    <body>
        <%
            String driver, url, usr, pwd, sql, idaluno;
            Connection connect;
            PreparedStatement select;
            ResultSet result;

            idaluno = request.getParameter("alt");

            driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            url = "jdbc:mysql://localhost:3306/atv_faculdade";
            usr = "root";
            pwd = "admin111";
            connect = DriverManager.getConnection(url, usr, pwd);

            sql = "SELECT * FROM alunos WHERE id = ?";
            select = connect.prepareStatement(sql);
            select.setString(1, idaluno);
            result = select.executeQuery();

            if (result.next()) {
        %>
        <div>
            <h1 class="title"> Alteração de dados </h1>
            <form method="post" action="update.jsp">
                <div class="cima">
                    <div class="cima-esq">
                        <label for="nome">Nome:</label>
                        <input id="nome" type="text" name="nomeUpdate" size="50" maxlength="50" value="<%= result.getString("nome")%>">
                    </div>

                    <div class="cima-dir">
                        <label for="cpf">CPF:</label>
                        <input id="cpf" type="text" name="cpfUpdate" size="50" maxlength="11" value="<%= result.getString("cpf")%>">
                    </div>
                </div>

                <div class="meio">
                    <div class="meio-esq">
                        <label for="telefone">Telefone:</label>
                        <input id="telefone" type="text" name="telefoneUpdate" size="50" maxlength="20" value="<%= result.getString("telefone")%>">
                    </div>

                    <div class="meio-dir">
                        <label for="email">E-mail:</label>
                        <input id="email" type="text" name="emailUpdate" size="50" maxlength="50" value="<%= result.getString("email")%>">
                    </div>
                </div>

                <div class="baixo">
                    <div class="input">  
                        <label for="nomecur">Curso:</label>

                        <select id="nomecur" name="namecurUpdate" required>
                            <option value="CC" 
                                    <% if (result.getString("nome_curso").equals("CC")) {
                                            out.print("selected");
                                        } %>>Ciências da Computação
                            </option>

                            <option value="TADS" 
                                    <% if (result.getString("nome_curso").equals("TADS")) {
                                            out.print("selected");
                                        } %>>Análise e Desenvolvimento de Sistemas
                            </option>
                            <option value="SI"
                                    <% if (result.getString("nome_curso").equals("SI"))
                                        out.print("selected");%>>Sistemas da Informação
                            </option>
                        </select>
                    </div>  
                    <input type="submit" value="Atualizar">
                    <input type="hidden" name="idaluno" value="<%= idaluno %>">
                </div>
                            
            </form>
        </div>
        <%
        } else {
        %>
        <p>Aluno não encontrado.</p>
        <%
            }
            connect.close();
        %>

    </body>
</html >
