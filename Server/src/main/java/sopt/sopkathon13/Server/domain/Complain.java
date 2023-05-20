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
public class Complain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complainId;

    @Column(nullable = false)
    @CreatedDate
    private LocalDate date;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long complainCount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "fromId", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private User fromUser;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "fromId", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private User toUser;

    public Complain(Long complainCount, User fromUser, User toUser) {
        this.complainCount = complainCount;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    public static Complain newInstance(Long complainCount, User fromUser, User toUser) {
        return new Complain(complainCount, fromUser, toUser);
    }
}
