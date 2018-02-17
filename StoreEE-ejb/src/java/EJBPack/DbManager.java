
package EJBPack;

import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

@Singleton
public class DbManager implements DbManagerLocal {
    
EntityManagerFactory emf;
EntityManager em;
EntityTransaction et;

    @Override
    public void openDb() {           
       emf = Persistence.createEntityManagerFactory("StoreEE-ejbPU");
       em = emf.createEntityManager();           
    }

    @Override
    public void closeDb() {   
        em.close();
        emf.close();        
    }
    
    @Override
    public void openTransaction() {      
       et = em.getTransaction();
       et.begin();
           
    }

    @Override
    public void closeTransaction() {
        et.commit();                
    }

    @Override
    public void addNewParameter(String name, int value) {
        Storeee newPar = new Storeee(name, value);
        if (em == null) openDb();
        openTransaction();
        em.persist(newPar);  
        closeTransaction();
        closeDb();
    }

    @Override
    public void deleteParameter(String name) {
        if (em == null) openDb();  
        openTransaction();
        Storeee delParameter = em.find(Storeee.class, name);
        em.persist(delParameter);
        em.remove(delParameter);
        closeTransaction();
        closeDb();
    }

    @Override
    public void updateParameter(String name, int value) {
        if (em == null) openDb(); 
        openTransaction();
        Storeee updParameter = em.find(Storeee.class, name);
        updParameter.setStorevalue(value);
        em.persist(updParameter);
        closeTransaction();
        closeDb(); 
    }

    @Override
   public Query findParameter (){
   Query query = em.createNamedQuery("Storeee.findAll");
   return query;
   }
    
    
    
    
    
    
    
}
