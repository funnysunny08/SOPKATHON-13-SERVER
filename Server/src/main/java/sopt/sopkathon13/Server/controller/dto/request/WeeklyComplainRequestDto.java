package sopt.sopkathon13.Server.controller.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WeeklyComplainRequestDto {
    @NotEmpty
    private LocalDate startDate;
    @NotEmpty
    private LocalDate endDate;
}
