package vn.edu.iuh.fit.service3_user.repositories;

import vn.edu.iuh.fit.service3_user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
