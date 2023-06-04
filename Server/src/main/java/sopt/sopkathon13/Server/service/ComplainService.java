package sopt.sopkathon13.Server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.sopkathon13.Server.domain.Complain;
import sopt.sopkathon13.Server.domain.User;
import sopt.sopkathon13.Server.exception.Error;
import sopt.sopkathon13.Server.exception.model.NotFoundException;
import sopt.sopkathon13.Server.infrastructure.ComplainRepository;
import sopt.sopkathon13.Server.infrastructure.UserRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ComplainService {
    private final ComplainRepository complainRepository;
    private final UserRepository userRepository;

    @Transactional
    public void create(int fromHomeNumber, int toHomeNumber) {
        User fromUser = userRepository.findByHomeNumber(fromHomeNumber)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));
        User toUser = userRepository.findByHomeNumber(toHomeNumber)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));
        Complain exComplain = complainRepository.findByFromUserAndToUser(fromUser, toUser);
        if (exComplain == null) { // 한 번도 찌른 적 없음
            Complain newComplain = new Complain(Long.valueOf(1), fromUser, toUser, LocalDate.now());
            complainRepository.save(newComplain);
        } else { // 찌른 적 있음
            complainRepository.updateComplainCount(exComplain.getComplainId(), exComplain.getComplainCount() + 1);
        }
        toUser.updateUserTodayCount();
    }
}
