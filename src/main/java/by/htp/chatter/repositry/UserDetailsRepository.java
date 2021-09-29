package by.htp.chatter.repositry;

import by.htp.chatter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<User, String> {
}
