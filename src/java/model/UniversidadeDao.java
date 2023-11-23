package model;

import javax.persistence.TypedQuery;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class UniversidadeDao {

    private EntityManager manager;

    private void connect() {
        manager = Persistence.createEntityManagerFactory("PROJETO-JAVA-EM-CAMADASPU").createEntityManager();
    }

    public int salvarFuncionario(Funcionario funcionario) {
        connect();

        try {

            manager.getTransaction().begin();
            manager.persist(funcionario);
            manager.getTransaction().commit();
            return 1;

        } catch (Exception erro) {

            return 0;

        }
    }

    public List<Funcionario> consultarFuncionario(String nome) {
        connect();
        TypedQuery<Funcionario> query = manager.createNamedQuery("Funcionario.findByPartialNome", Funcionario.class);
        query.setParameter("partialNome", "%" + nome + "%");
        
        return query.getResultList();
    }
    
    public int verificarCpf(String cpf) {
    connect();

    TypedQuery<Funcionario> query = manager.createNamedQuery("Funcionario.findByCpf", Funcionario.class);
    query.setParameter("cpf", cpf);

    try {
        
        Funcionario funcionario = query.getSingleResult();
        return 1;
    } catch (NoResultException e) {
        
        return 0;
    }
}


    public int excluirFuncionario(Integer matricula) {
        connect();

        TypedQuery<Funcionario> query = manager.createQuery("SELECT f FROM Funcionario f WHERE f.matricula = :matricula", Funcionario.class);
        query.setParameter("matricula", matricula);

        Funcionario funcionario = query.getSingleResult();

        try {
            manager.getTransaction().begin();
            manager.remove(funcionario);
            manager.getTransaction().commit();
            return 1;
        } catch (Exception erro) {
            return 0;
        }
    }

}
