package sopt.sopkathon13.Server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.sopkathon13.Server.infrastructure.ComplainRepository;

@Service
@RequiredArgsConstructor
public class ComplainService {
    private ComplainRepository complainRepository;

    @Transactional
    public void create(int fromHomeNumber, int toHomeNumber) {

    }
}
