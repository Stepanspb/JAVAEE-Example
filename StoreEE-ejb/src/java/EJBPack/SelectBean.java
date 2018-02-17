
package EJBPack;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;


@Stateless
public class SelectBean implements SelectBeanLocal {

    @EJB
    DbManagerLocal dbm;

    @Override
    public List<Storeee> getResultList() {
        List<Storeee> list;
        dbm.openDb();
        Query query = dbm.findParameter();
        list = query.getResultList();
        return list;
    }

    public String showResults(String name) {        
        boolean listIsEmpty = true;
        List<Storeee> list = getResultList();
        if (list == null) return "DataBase is Empty";
        StringBuilder sb = new StringBuilder();
        sb.append("\n <table border = '1'> \n <tr> \n <hd>Name</hd> <hd>Value</hd> \n</tr>");
        if (name == null || name.trim().isEmpty()) {
            for (Storeee st : list) {
                sb.append("<tr> \n <td>").append(st.getStorename()).append("</td><td>")
                        .append(st.getStorevalue()).append("</td></tr>\n");
            }
        } else {
            for (Storeee st : list) {
                if (st.getStorename().startsWith(name)){
                    listIsEmpty = false;
                    sb.append("<tr> \n <td>").append(st.getStorename()).append("</td><td>")
                      .append(st.getStorevalue()).append("</td></tr>\n");
                }
            }
            if (listIsEmpty) return "No mathes in database";
        }
        sb.append("</table>");
        dbm.closeDb();
        return sb.toString();
    }
}
