package sopt.sopkathon13.Server.infrastructure;

import org.springframework.data.repository.Repository;
import sopt.sopkathon13.Server.domain.User;

import java.util.Optional;
import java.util.List;


public interface UserRepository extends Repository<User, Integer> {
    // CREATE

    // READ
    User findByHomeNumber(int homeNumber);

    List<User> findByKeyNumber(String keyNumber);


    // UPDATE


    // DELETE
}
