package sopt.sopkathon13.Server.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WeeklyComplainResponseDto {
    private int averageCount;
    private int complainedDays;
    private int complainedCount;

    public static WeeklyComplainResponseDto of (int averageCount, int complainedDays, int complainedCount){
        return new WeeklyComplainResponseDto(averageCount, complainedDays, complainedCount);
    }
}
