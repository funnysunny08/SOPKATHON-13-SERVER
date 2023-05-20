package sopt.sopkathon13.Server.controller.dto.response;

import lombok.*;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodayComplainResponseDto {
    @Size(max = 2)
    private List<Long> up;

    @Size(max = 2)
    private List<Long> right;

    @Size(max = 2)
    private List<Long> left;

    @Size(max = 2)
    private List<Long> down;
    private Long receiveCount;
}
