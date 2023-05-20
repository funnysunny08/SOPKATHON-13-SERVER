package sopt.sopkathon13.Server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.sopkathon13.Server.controller.dto.request.WeeklyComplainRequestDto;
import sopt.sopkathon13.Server.controller.dto.response.TodayComplainResponseDto;
import sopt.sopkathon13.Server.controller.dto.response.WeeklyComplainResponseDto;
import sopt.sopkathon13.Server.domain.Complain;
import sopt.sopkathon13.Server.domain.User;
import sopt.sopkathon13.Server.infrastructure.ComplainRepository;
import sopt.sopkathon13.Server.infrastructure.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ComplainRepository complainRepository;

    @Transactional
    public TodayComplainResponseDto findTodayComplain(int fromHomeNumber){
        User upUser = userRepository.findByHomeNumber(fromHomeNumber + 100);
        User rightUser = userRepository.findByHomeNumber(fromHomeNumber + 1);
        User downUser = userRepository.findByHomeNumber(fromHomeNumber - 100);
        User leftUser = userRepository.findByHomeNumber(fromHomeNumber - 1);

        List<Long> up = new ArrayList<>();
        up.add((long)(fromHomeNumber + 100));
        up.add(upUser.getTodayGetComplain());

        List<Long> right = new ArrayList<>();
        right.add((long)(fromHomeNumber + 1));
        right.add(rightUser.getTodayGetComplain());

        List<Long> down = new ArrayList<>();
        down.add((long)(fromHomeNumber - 100));
        down.add(downUser.getTodayGetComplain());

        List<Long> left = new ArrayList<>();
        left.add((long)(fromHomeNumber - 1));
        left.add(leftUser.getTodayGetComplain());

        User fromUser = userRepository.findByHomeNumber(fromHomeNumber);

        return TodayComplainResponseDto.builder()
                .up(up)
                .right(right)
                .down(down)
                .left(left)
                .receiveCount(fromUser.getTodayGetComplain())
                .build();
    }

    @Transactional
    public int login(String keyNumber) {
        User user = userRepository.findByKeyNumber(keyNumber).get(0);
        return user.getHomeNumber();
    }

    @Transactional
    public WeeklyComplainResponseDto readWeeklyComplain (int homeNumber, WeeklyComplainRequestDto weeklyComplainRequestDto) {
        User user = userRepository.findByHomeNumber(homeNumber);
        List<Complain> complainedMe = complainRepository.findByDateBetweenAndToUser(weeklyComplainRequestDto.getStartDate(), weeklyComplainRequestDto.getEndDate(), user);
        int complainedCount = 0;
        for (int i = 0; i < complainedMe.size(); i++) {
            complainedCount += complainedMe.get(i).getComplainCount();
        }
        int complainedDays = complainedMe.size();
        List<Complain> complainAll = complainRepository.findByDateBetween(weeklyComplainRequestDto.getStartDate(), weeklyComplainRequestDto.getEndDate());
        int all = 0;
        for (int i = 0; i < complainAll.size(); i++) {
            all += complainAll.get(i).getComplainCount();
        }
        int averageCount = all / complainAll.size();
        return WeeklyComplainResponseDto.of(averageCount, complainedDays, complainedCount);
    }
}
