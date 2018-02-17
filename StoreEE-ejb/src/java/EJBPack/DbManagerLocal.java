/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBPack;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Local
public interface DbManagerLocal {    
   void openDb();
   
   void closeDb();
   
   void addNewParameter(String name, int value);
  
   void deleteParameter (String name);
   
   void updateParameter (String name, int value);

   Query findParameter ();
   
   void openTransaction();
   
   public void closeTransaction();
   
   
}
