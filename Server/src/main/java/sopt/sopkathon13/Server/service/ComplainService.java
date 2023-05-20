package sopt.sopkathon13.Server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.sopkathon13.Server.domain.Complain;
import sopt.sopkathon13.Server.domain.User;
import sopt.sopkathon13.Server.infrastructure.ComplainRepository;
import sopt.sopkathon13.Server.infrastructure.UserRepository;

@Service
@RequiredArgsConstructor
public class ComplainService {
    private ComplainRepository complainRepository;
    private UserRepository userRepository;

    @Transactional
    public void create(int fromHomeNumber, int toHomeNumber) {
        User fromUser = userRepository.findByHomeNumber(fromHomeNumber);
        User toUser = userRepository.findByHomeNumber(toHomeNumber);
        Complain exComplain = complainRepository.findByFromHomeNumberAndToHomeNumber(fromUser, toUser);
        if (exComplain == null) { // 한 번도 찌른 적 없음

            Complain newComplain = new Complain(Long.valueOf(1), fromUser, toUser);
            complainRepository.save(newComplain);
        } else { // 찌른 적 있음
            complainRepository.updateComplainCount(exComplain.getComplainId(), exComplain.getComplainCount() + 1);
        }
    }
}
