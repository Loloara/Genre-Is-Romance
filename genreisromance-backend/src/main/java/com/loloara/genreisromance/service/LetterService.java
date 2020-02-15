package com.loloara.genreisromance.service;

import com.loloara.genreisromance.model.Letter;
import com.loloara.genreisromance.repository.LetterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LetterService {
    private final LetterRepository letterRepository;

    public LetterService (LetterRepository letterRepository) {
        this.letterRepository = letterRepository;
    }

    public Letter save (Letter letter) {
        return letterRepository.save(letter);
    }

    public Letter findById (Long letterId) {
        log.info("Find letter by id : {}", letterId);
        return letterRepository.findById(letterId)
                    .orElseThrow(IllegalArgumentException::new);
    }

    public boolean existsById (Long letterId) {
        return letterRepository.existsById(letterId);
    }
}