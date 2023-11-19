package controler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControleAlunos", urlPatterns = {"/ControleAlunos"})
public class ControleAlunos extends HttpServlet {
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Criando a variavel:
        String flag;
        
        // Recebendo o conteudo da varivel flag que veio da pagina html:
        flag = request.getParameter("cadastro_alunos");
        
        // Verifica se dentro de flag vem com o conteudo igual a cadastro:
        if(flag.equals("cadastro")){
            // Criando as variaveis que receberão os dados:
            String nome, cpf, email, curso_escolhido;
            Integer telefone;
            
            // Recebendo os dados digitados no formulario:
            nome = request.getParameter("nome");
            cpf = request.getParameter("cpf_aluno");
            email = request.getParameter("emailaluno");
            curso_escolhido = request.getParameter("nomecur");
            telefone = Integer.parseInt(request.getParameter("telaluno"));
            
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
