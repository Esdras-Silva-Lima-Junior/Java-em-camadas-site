package controler;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Funcionario;
import model.UniversidadeDao;

@WebServlet(name = "ControleFuncionario", urlPatterns = {"/ControleFuncionario"})
public class ControleFuncionario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Criando e recebendo a flag
        String flag = request.getParameter("flag");
        
        if (flag != null) {
            if (flag.equals("cadastro_Funcionario")) {
                //  Aqui fica a parte de cadastro do aluno
                String nome, cpf, email, cargo, mensagem, telefone;
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
                    cargo = request.getParameter("nomecur");

                    Funcionario funcionario = new Funcionario();
                    funcionario.setNome(nome);
                    funcionario.setCpf(cpf);
                    funcionario.setEmail(email);
                    funcionario.setTelefone(telefone);
                    funcionario.setCargo(cargo);
                    result = new UniversidadeDao().salvarFuncionario(funcionario);

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
                List<Funcionario> listaFuncionario = new UniversidadeDao().consultarFuncionario(nome);

                // Verifica se a lista está vazia
                 if (listaFuncionario.isEmpty()) {
                    mensagem = "Nenhum funcionario encontrado";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher disp = request.getRequestDispatcher("mensagem.jsp");
                    disp.forward(request, response);
                 } else {
                    request.setAttribute("funcionariosEncontrados", listaFuncionario);
                    RequestDispatcher disp = request.getRequestDispatcher("consultar.jsp");
                    disp.forward(request, response);
                }
            } else if (flag.equals("excluir")) {
                 // Aqui fica a parte de exclusão do aluno
                String mensagem;
                Integer matricula;
                
                matricula = Integer.parseInt(request.getParameter("matricula"));

                int result = new UniversidadeDao().excluirFuncionario(matricula);
                if (result == 1) {
                    mensagem = "Funcionarios excluido com sucesso!!";
                    request.setAttribute("mensagem", mensagem);

                    RequestDispatcher disp = request.getRequestDispatcher("mensagem.jsp");
                    disp.forward(request, response);
                } else {
                    mensagem = "Funcionario não encontrado.";
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
