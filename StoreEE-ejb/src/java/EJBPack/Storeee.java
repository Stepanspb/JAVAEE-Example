
package EJBPack;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "STOREEE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Storeee.findAll", query = "SELECT s FROM Storeee s")
    , @NamedQuery(name = "Storeee.findByStorename", query = "SELECT s FROM Storeee s WHERE s.storename = :storename")
    , @NamedQuery(name = "Storeee.findByStorevalue", query = "SELECT s FROM Storeee s WHERE s.storevalue = :storevalue")})
public class Storeee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "STORENAME")
    private String storename;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STOREVALUE")
    private int storevalue;

    public Storeee() {
    }

    public Storeee(String storename) {
        this.storename = storename;
    }

    public Storeee(String storename, int storevalue) {
        this.storename = storename;
        this.storevalue = storevalue;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public int getStorevalue() {
        return storevalue;
    }

    public void setStorevalue(int storevalue) {
        this.storevalue = storevalue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storename != null ? storename.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Storeee)) {
            return false;
        }
        Storeee other = (Storeee) object;
        if ((this.storename == null && other.storename != null) || (this.storename != null && !this.storename.equals(other.storename))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EJBPack.Storeee[ storename=" + storename + " ]";
    }
    
}
