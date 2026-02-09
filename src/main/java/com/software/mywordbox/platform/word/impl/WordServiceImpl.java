package com.software.mywordbox.platform.word.impl;

import com.software.mywordbox.library.security.JwtUtil;
import com.software.mywordbox.platform.word.api.WordDto;
import com.software.mywordbox.platform.word.api.WordMapper;
import com.software.mywordbox.platform.word.api.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;
    private final WordMapper wordMapper;

    @Override
    public Page<WordDto> getAllList(Pageable pageable) {
        String userId = JwtUtil.extractUserIdAndIfAnonymousThrow();
        Page<Word> words = wordRepository.findByUserId(userId, pageable);
        return words.map(wordMapper::entityToDto);
    }

    @Override
    public WordDto getById(String id) {
        Word word = wordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Word not found with id: " + id));
        return wordMapper.entityToDto(word);
    }

    @Override
    public WordDto create(WordDto dto) {
        String userId = JwtUtil.extractUserIdAndIfAnonymousThrow();

        Word word = wordMapper.toEntity(dto);
        word.setUserId(userId);

        if (word.getAddedDate() == null) {
            word.setAddedDate(LocalDate.now());
        }

        Word saved = wordRepository.save(word);
        return wordMapper.entityToDto(saved);
    }

    @Override
    public WordDto update(String id, WordDto dto) {
        Word existingWord = wordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Word not found with id: " + id));

        existingWord.setEnglishWord(dto.getEnglishWord());
        existingWord.setTurkishMeaning(dto.getTurkishMeaning());
        existingWord.setAddedDate(dto.getAddedDate());

        Word updated = wordRepository.save(existingWord);
        return wordMapper.entityToDto(updated);
    }

    @Override
    public void delete(String id) {
        if (!wordRepository.existsById(id)) {
            throw new RuntimeException("Word not found with id: " + id);
        }
        wordRepository.deleteById(id);
    }

    @Override
    public Page<WordDto> getWordsByDate(LocalDate date, Pageable pageable) {
        String userId = JwtUtil.extractUserIdAndIfAnonymousThrow();
        Page<Word> words = wordRepository.findByUserIdAndAddedDate(userId, date, pageable);
        return words.map(wordMapper::entityToDto);
    }

    @Override
    public Page<WordDto> getWordsByDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        String userId = JwtUtil.extractUserIdAndIfAnonymousThrow();
        Page<Word> words = wordRepository.findByUserIdAndAddedDateBetween(userId, startDate, endDate, pageable);
        return words.map(wordMapper::entityToDto);
    }

    @Override
    public Page<WordDto> getWordsLastMonth(Pageable pageable) {
        String userId = JwtUtil.extractUserIdAndIfAnonymousThrow();
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(1);

        Page<Word> words = wordRepository.findByUserIdAndAddedDateBetween(userId, startDate, endDate, pageable);
        return words.map(wordMapper::entityToDto);
    }
}