package sopt.sopkathon13.Server.infrastructure;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import sopt.sopkathon13.Server.domain.Complain;
import sopt.sopkathon13.Server.domain.User;

public interface ComplainRepository extends Repository<Complain, Long> {
    // CREATE
    void save(Complain complain);

    // READ
//    @Modifying
//    @Query(value = "select c.complain_id from complain c where c.from_home_number = :fromHomeNumber and c.to_home_number = :toHomeNumber", nativeQuery = true)
    Complain findByFromUserAndToUser(User fromUser, User toUser);
    Complain findById(Long id);

    // UPDATE
    @Modifying
    @Query("update Complain c set c.complainCount = :newCount where c.complainId = :cId")
    void updateComplainCount(Long cId, Long newCount);

    // DELETE
}
