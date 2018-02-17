package EJBPack;

import javax.ejb.Local;


@Local
public interface UpdateBeanLocal {

    String ParameterProcessing(String name, String value);

    boolean checkName(String name);

    boolean checkValue(String value);     
    
    String ParameterDel (String name);
   
}
