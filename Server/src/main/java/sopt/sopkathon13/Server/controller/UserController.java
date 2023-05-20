package sopt.sopkathon13.Server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopt.sopkathon13.Server.common.dto.ApiResponse;
import sopt.sopkathon13.Server.controller.dto.request.LoginRequestDto;
import sopt.sopkathon13.Server.controller.dto.response.TodayComplainResponseDto;
import sopt.sopkathon13.Server.exception.Success;
import sopt.sopkathon13.Server.service.UserService;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/{fromHomeNumber}/complain")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<TodayComplainResponseDto> readTodayComplain(@PathVariable("fromHomeNumber") int fromHomeNumber){
        return ApiResponse.success(Success.COMPLAIN_FIND_SUCCESS, userService.findTodayComplain(fromHomeNumber));
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse login(@RequestBody final LoginRequestDto loginRequestDto) {
        System.out.println("dsf");
        int homeNumber = userService.login(loginRequestDto.getKeyNumber());
        return ApiResponse.success(Success.SIGNIN_SUCESS, homeNumber);
    }
}
