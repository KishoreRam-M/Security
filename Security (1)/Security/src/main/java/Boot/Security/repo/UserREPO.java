package Boot.Security.repo;

import Boot.Security.mode.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserREPO extends JpaRepository<Users,Integer> {
    Users findByUsername(String username);
}
