
package sec.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sec.project.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByUser(String user);
    
    @Query("SELECT a FROM Contact a WHERE a.user=?1 AND a.name LIKE ?2")
    List<Contact> findByName(String user, String name);
}
