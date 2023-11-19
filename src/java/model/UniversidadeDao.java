package model;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class UniversidadeDao {
    
    private EntityManager manager; 
    
    private void connect(){
        manager = Persistence.createEntityManagerFactory("WEB-PROJECT-CLASSESPU").createEntityManager();
    }
}