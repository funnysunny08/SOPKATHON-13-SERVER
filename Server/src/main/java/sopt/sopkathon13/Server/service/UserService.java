package sopt.sopkathon13.Server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.sopkathon13.Server.controller.dto.request.WeeklyComplainRequestDto;
import sopt.sopkathon13.Server.common.CalendarUtil;
import sopt.sopkathon13.Server.controller.dto.response.ComplainReportResponseDto;
import sopt.sopkathon13.Server.controller.dto.response.TodayComplainResponseDto;
import sopt.sopkathon13.Server.controller.dto.response.WeeklyComplainResponseDto;
import sopt.sopkathon13.Server.domain.Complain;
import sopt.sopkathon13.Server.domain.User;
import sopt.sopkathon13.Server.exception.Error;
import sopt.sopkathon13.Server.exception.model.NotFoundException;
import sopt.sopkathon13.Server.infrastructure.ComplainRepository;
import sopt.sopkathon13.Server.infrastructure.UserRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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

    public List<ComplainReportResponseDto> getAllComplainReport(int homeNumber) {
        List<ComplainReportResponseDto> result = new ArrayList<>();

        User user = userRepository.findByHomeNumber(homeNumber);

        LocalDate date = user.getSignedAt();

        for(; date.isBefore(LocalDate.now()); date = date.plusDays(7)){
            List<Integer> week = CalendarUtil.getCurrentWeekOfMonth(date.getYear(), date.getMonth().getValue(), date.getDayOfMonth());

            LocalDate thisDate;
            for(thisDate = date; thisDate.getDayOfWeek() != DayOfWeek.SUNDAY; thisDate = thisDate.plusDays(1));
            LocalDate sunday = thisDate;
            for(thisDate = date; thisDate.getDayOfWeek() != DayOfWeek.MONDAY; thisDate = thisDate.minusDays(1));
            LocalDate monday = thisDate;

            result.add(ComplainReportResponseDto.builder()
                            .month(String.valueOf(week.get(0)))
                            .week(String.valueOf(week.get(1)))
                            .first(String.valueOf(monday.getMonth().getValue()) + "." + String.valueOf(monday.getDayOfMonth()))
                            .last(String.valueOf(sunday.getMonth().getValue()) + "." + String.valueOf(sunday.getDayOfMonth()))
                            .startDate(monday)
                            .endDate(sunday)
                    .build());
        }

        if(result.isEmpty())
            return Collections.emptyList();

        return result;
    }

    @Transactional
    public int login(String keyNumber) {
        User user = userRepository.findByKeyNumber(keyNumber)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));
        return user.getHomeNumber();
    }

    @Transactional
    public WeeklyComplainResponseDto readWeeklyComplain (int homeNumber, String startDate, String endDate) {
        User user = userRepository.findByHomeNumber(homeNumber)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));
        List<Complain> complainedMe = complainRepository.findByDateBetweenAndToUser(LocalDate.parse(startDate), LocalDate.parse(endDate), user);
        int complainedCount = 0;
        for (int i = 0; i < complainedMe.size(); i++) {
            complainedCount += complainedMe.get(i).getComplainCount();
        }
        int complainedDays = complainedMe.size();
        List<Complain> complainAll = complainRepository.findByDateBetween(LocalDate.parse(startDate), LocalDate.parse(endDate));
        int all = 0;
        for (int i = 0; i < complainAll.size(); i++) {
            all += complainAll.get(i).getComplainCount();
        }
        int averageCount = all / complainAll.size();
        return WeeklyComplainResponseDto.of(averageCount, complainedDays, complainedCount);
    }
}
