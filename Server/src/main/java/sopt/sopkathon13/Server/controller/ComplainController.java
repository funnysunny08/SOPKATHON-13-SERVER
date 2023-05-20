package sopt.sopkathon13.Server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopt.sopkathon13.Server.common.dto.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/complain")
public class ComplainController {
    private final ComplainService complainService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(@RequestHeader int homeNumber, @RequestBody int toHomeNumber) {

    }
}
