package sopt.sopkathon13.Server.controller.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ComplainRequestDto {
    @NotBlank
    private int toHomeNumber;
}
