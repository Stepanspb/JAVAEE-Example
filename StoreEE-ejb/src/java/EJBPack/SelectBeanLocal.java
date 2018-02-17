
package EJBPack;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;

/**
 *
 * @author DNS
 */
@Local
public interface SelectBeanLocal {
   
List <Storeee> getResultList ();   
 
String showResults (String name);
    
}
