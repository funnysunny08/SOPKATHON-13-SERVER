package sopt.sopkathon13.Server.infrastructure;

import org.springframework.data.repository.Repository;
import sopt.sopkathon13.Server.domain.Complain;
import sopt.sopkathon13.Server.domain.User;

import java.time.LocalDate;
import java.util.List;

public interface ComplainRepository extends Repository<Complain, Long> {
    // CREATE

    // READ
    List<Complain> findAllByToUser(User toUser);
    List<Complain> findByDateBetweenAndToUser(LocalDate fromDate, LocalDate toDate, User toUser);
    List<Complain> findByDateBetween(LocalDate fromDate, LocalDate toDate);
    // UPDATE

    // DELETE
}
