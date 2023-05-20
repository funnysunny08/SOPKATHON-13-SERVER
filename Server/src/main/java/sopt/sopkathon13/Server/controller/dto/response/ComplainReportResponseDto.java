package sopt.sopkathon13.Server.controller.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ComplainReportResponseDto {
    private String month;
    private String week;
    private String first;
    private String last;
    private LocalDate startDate;
    private LocalDate endDate;
}
