
package sec.project.domain;

import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Contact extends AbstractPersistable<Long>{
    private String user;
    private String name;
    private String address;
    
    public Contact () { super();}
    public Contact(String user, String name, String address) {
        this.user = user;
        this.name = name;
        this.address = address;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    @Override
    public String toString() {
        return this.name + ": " + this.address + " ";
    }
    
}
