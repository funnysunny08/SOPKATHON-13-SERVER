package sopt.sopkathon13.Server.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @Column(nullable = false)
    private int homeNumber;

    @Column(nullable = false)
    private String keyNumber;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long todayGetComplain;

    @Column(nullable = false)
    @CreatedDate
    private LocalDate signedAt;

    public User(int homeNumber, String keyNumber, Long todayGetComplain) {
        this.homeNumber = homeNumber;
        this.keyNumber = keyNumber;
        this.todayGetComplain = todayGetComplain;
    }

    public static User newInstance(int homeNumber, String keyNumber, Long todayGetComplain) {
        return new User(homeNumber, keyNumber, todayGetComplain);
    }
}
