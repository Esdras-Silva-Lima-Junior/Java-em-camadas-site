package controler;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Aluno;
import model.UniversidadeDao;

@WebServlet(name = "ControleAlunos", urlPatterns = {"/ControleAlunos"})
public class ControleAlunos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Criando e recebendo a flag
        String flag = request.getParameter("flag");
        
        if (flag != null) {
            if (flag.equals("cadastro_aluno")) {
                //  Aqui fica a parte de cadastro do aluno
                String nome, cpf, email, curso, mensagem, telefone;
                int result, verifica;

                // Recebendo os dados
                nome = request.getParameter("nome");
                cpf = request.getParameter("cpf_aluno");
                
                verifica = new UniversidadeDao().verificarCpf(cpf);
                
                // Verifica se o usuário já está cadastrado via cpf
                if (verifica == 1) {
                    mensagem = "Usuário já cadastrado";
                    request.setAttribute("mensagem", mensagem);

                    RequestDispatcher disp = request.getRequestDispatcher("mensagem.jsp");

                    disp.forward(request, response);
                } else {
                    email = request.getParameter("emailaluno");
                    telefone = request.getParameter("telaluno");
                    curso = request.getParameter("nomecur");

                    Aluno aluno = new Aluno();
                    aluno.setNome(nome);
                    aluno.setCpf(cpf);
                    aluno.setEmail(email);
                    aluno.setTelefone(telefone);
                    aluno.setCurso(curso);
                    result = new UniversidadeDao().salvarAluno(aluno);

                    // Mensagem de confirmação ou Erro.
                    if (result == 1) {
                        mensagem = "Cadastro realizado com sucesso!";
                    } else {
                        mensagem = "Erro ao tentar cadastrar os dados.";
                    }

                    request.setAttribute("mensagem", mensagem);

                    RequestDispatcher disp = request.getRequestDispatcher("mensagem.jsp");

                    disp.forward(request, response);
                }
            } else if (flag.equals("consultar")) {
                // Aqui fica a parte de consulta do aluno
                
                String mensagem, nome;
                nome = request.getParameter("nome");
                List<Aluno> listaAluno = new UniversidadeDao().consultarAluno(nome);

                // Verifica se a lista está vazia
                 if (listaAluno.isEmpty()) {
                    mensagem = "Nenhum aluno encontrado";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher disp = request.getRequestDispatcher("mensagem.jsp");
                    disp.forward(request, response);
                 } else {
                    request.setAttribute("alunosEncontrados", listaAluno);
                    RequestDispatcher disp = request.getRequestDispatcher("consultar.jsp");
                    disp.forward(request, response);
                }
            } else if (flag.equals("excluir")) {
                 // Aqui fica a parte de exclusão do aluno
                String mensagem;
                Integer ra;
                
                ra = Integer.parseInt(request.getParameter("ra"));

                int result = new UniversidadeDao().excluirAluno(ra);
                if (result == 1) {
                    mensagem = "Aluno excluido com sucesso!!";
                    request.setAttribute("mensagem", mensagem);

                    RequestDispatcher disp = request.getRequestDispatcher("mensagem.jsp");
                    disp.forward(request, response);
                } else {
                    mensagem = "Aluno não encontrado.";
                    request.setAttribute("mensagem", mensagem);

                    RequestDispatcher disp = request.getRequestDispatcher("mensagem.jsp");
                    disp.forward(request, response);
                }
            }
        } else {
             String mensagem = "Flag nula";

            request.setAttribute("mensagem", mensagem);

            RequestDispatcher disp = request.getRequestDispatcher("mensagem.jsp");
            disp.forward(request, response);
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
