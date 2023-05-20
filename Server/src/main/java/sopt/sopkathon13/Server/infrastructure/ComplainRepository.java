package sopt.sopkathon13.Server.infrastructure;

import org.springframework.data.repository.Repository;
import sopt.sopkathon13.Server.domain.Complain;
import sopt.sopkathon13.Server.domain.User;

import java.util.List;

public interface ComplainRepository extends Repository<Complain, Long> {
    // CREATE

    // READ
    List<Complain> findAllByToUser(User toUser);

    // UPDATE

    // DELETE
}
