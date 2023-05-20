package sopt.sopkathon13.Server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopt.sopkathon13.Server.common.dto.ApiResponse;
import sopt.sopkathon13.Server.controller.dto.request.ComplainRequestDto;
import sopt.sopkathon13.Server.exception.Success;
import sopt.sopkathon13.Server.service.ComplainService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/complain")
public class ComplainController {
    private final ComplainService complainService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(@RequestHeader("homeNumber") Integer homeNumber, @RequestBody final ComplainRequestDto complainRequestDto) {

        complainService.create(homeNumber, complainRequestDto.getToHomeNumber());
        return ApiResponse.success(Success.MAKE_COMPLAIN_SUCCESS);
    }
}
