package Spring.security._FA.repo;

import Spring.security._FA.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface UsersRepo extends JpaRepository<Users ,Long> {
    Users findByEmail(String email);
}
