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
    @Modifying
    @Query(value = "select Complain c where c.fromHomeNumber = :fromHomeNumber and c.toHomeNumber = :toHomeNumber", nativeQuery = true)
    Complain findByFromHomeNumberAndToHomeNumber(User fromHomeNumber, User toHomeNumber);

    // UPDATE
    @Modifying
    @Query("update Complain c set c.complainCount = :newCount where c.complainId = :cId")
    void updateComplainCount(Long cId, Long newCount);

    // DELETE
}
