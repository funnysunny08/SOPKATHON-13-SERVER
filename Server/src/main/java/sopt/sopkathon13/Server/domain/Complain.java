package sopt.sopkathon13.Server.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Complain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complainId;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long complainCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fromHomeNumber", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private User fromUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "toHomeNumber", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private User toUser;

    public Complain(Long complainCount, User fromUser, User toUser, LocalDate date) {
        this.date = date;
        this.complainCount = complainCount;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    public static Complain newInstance(Long complainCount, User fromUser, User toUser, LocalDate date) {
        return new Complain(complainCount, fromUser, toUser, date);
    }

}
