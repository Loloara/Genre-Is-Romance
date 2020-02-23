package com.loloara.genreisromance.service;

import com.loloara.genreisromance.model.MatchTheDay;
import com.loloara.genreisromance.repository.MatchTheDayRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MatchTheDayService {

    private final MatchTheDayRepository matchTheDayRepository;

    public MatchTheDayService(MatchTheDayRepository matchTheDayRepository) {
        this.matchTheDayRepository = matchTheDayRepository;
    }

    public MatchTheDay save(MatchTheDay matchTheDay) {
        return matchTheDayRepository.save(matchTheDay);
    }

    public void saveAll(List<MatchTheDay> matchTheDays) {
        matchTheDayRepository.saveAll(matchTheDays);
    }

    public MatchTheDay findById(Long matchTheDayId) {
        log.info("find MatchTheDay by matchTheDayId : {}", matchTheDayId);
        return matchTheDayRepository.findById(matchTheDayId)
                .orElseThrow(IllegalArgumentException::new);
    }
}