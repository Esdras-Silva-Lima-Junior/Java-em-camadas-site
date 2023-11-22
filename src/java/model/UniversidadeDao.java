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

    public int salvarAluno(Aluno aluno) {
        connect();

        try {

            manager.getTransaction().begin();
            manager.persist(aluno);
            manager.getTransaction().commit();
            return 1;

        } catch (Exception erro) {

            return 0;

        }
    }

    public List<Aluno> consultarAluno(String nome) {
        connect();
        TypedQuery<Aluno> query = manager.createNamedQuery("Aluno.findByPartialNome", Aluno.class);
        query.setParameter("partialNome", "%" + nome + "%");
        
        return query.getResultList();
    }
    
    public int verificarCpf(String cpf) {
    connect();

    TypedQuery<Aluno> query = manager.createNamedQuery("Aluno.findByCpf", Aluno.class);
    query.setParameter("cpf", cpf);

    try {
        
        Aluno aluno = query.getSingleResult();
        return 1;
    } catch (NoResultException e) {
        
        return 0;
    }
}


    public int excluirAluno(Integer ra) {
        connect();

        TypedQuery<Aluno> query = manager.createQuery("SELECT a FROM Aluno a WHERE a.ra = :ra", Aluno.class);
        query.setParameter("ra", ra);

        Aluno aluno = query.getSingleResult();

        try {
            manager.getTransaction().begin();
            manager.remove(aluno);
            manager.getTransaction().commit();
            return 1;
        } catch (Exception erro) {
            return 0;
        }
    }

}
