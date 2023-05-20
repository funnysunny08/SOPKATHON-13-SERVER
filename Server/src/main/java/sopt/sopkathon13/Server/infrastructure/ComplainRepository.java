package sopt.sopkathon13.Server.infrastructure;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import sopt.sopkathon13.Server.domain.Complain;

public interface ComplainRepository extends Repository<Complain, Long> {
    // CREATE
    void save(Complain complain);

    // READ
    Complain findByFromIdAndToId(Long fromId, Long toId);

    // UPDATE
    @Modifying
    @Query("update Complain c set c.complainCount = :newCount where c.complainId = :cId")
    void updateComplainCount(Long cId, Long newCount);

    // DELETE
}
