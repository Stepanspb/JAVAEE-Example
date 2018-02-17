package EJBPack;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;


@Stateless
public class UpdateBean implements UpdateBeanLocal {
@EJB
DbManagerLocal dbml;

    @Override
    public String ParameterProcessing(String name, String value) {
        boolean update = false;        
        String message = "";
        if (checkName(name) && checkValue(value)) {
            dbml.openDb();
            Query query = dbml.findParameter();
            List<Storeee> list = query.getResultList();
            for (Storeee st : list) {
                if (st.getStorename().equals(name)) {
                    dbml.updateParameter(name, Integer.parseInt(value));
                    update = true;
                    message = "Parameter from DataBase was updated";
                }
            }
            if (!update) {
                dbml.addNewParameter(name, Integer.parseInt(value));
                message = "New Parameter was added in DataBase";
            }
        }        
        if (message.trim().isEmpty()) return " Parameter's name or value is inccorect";
        return message;
    }
    
    @Override
    public String ParameterDel(String name) {
        String message = "";
        if (checkName(name)){      
        dbml.openDb();
        Query query = dbml.findParameter();
        List<Storeee> list = query.getResultList();
        for (Storeee st : list) {
            if (st.getStorename().equals(name)) {                 
                dbml.deleteParameter(name);
                message = "Parameter was deleted from DataBase";
            }
        }
        }
        if (message.trim().isEmpty()) {
            return " Parameter is can't deleted";
        }    
        return message;
    }

    @Override
    public boolean checkName(String name) {
        if (name != null && !(name.trim().isEmpty())){
            name = name.trim();
        if (name.length()<33)
        return true;       
        }
        return false;
    }

    @Override
    public boolean checkValue(String value) {
       try { 
          Integer.valueOf(value);
       } catch (NumberFormatException ex){
           return false;
       } 
       return true;
    }

  
    
}
