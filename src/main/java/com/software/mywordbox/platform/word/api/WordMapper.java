package com.software.mywordbox.platform.word.api;

import com.software.mywordbox.library.abstraction.AbstractEntityMapper;
import com.software.mywordbox.library.abstraction.AbstractWebMapper;
import com.software.mywordbox.platform.word.impl.Word;
import com.software.mywordbox.platform.word.web.WordRequest;
import com.software.mywordbox.platform.word.web.WordResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class WordMapper implements AbstractEntityMapper<Word, WordDto>, AbstractWebMapper<WordDto, WordRequest, WordResponse> {

    @Override
    public Word toEntity(WordDto dto) {
        Word word = new Word();
        word.setId(dto.getId());
        word.setUserId(dto.getUserId());
        word.setEnglishWord(dto.getEnglishWord());
        word.setTurkishMeaning(dto.getTurkishMeaning());
        word.setAddedDate(dto.getAddedDate());
        word.setCreated(dto.getCreated());
        word.setModified(dto.getModified());
        return word;
    }

    @Override
    public WordDto entityToDto(Word entity) {
        return WordDto.builder()
                .id(entity.getId())
                .created(entity.getCreated())
                .modified(entity.getModified())
                .userId(entity.getUserId())
                .englishWord(entity.getEnglishWord())
                .turkishMeaning(entity.getTurkishMeaning())
                .addedDate(entity.getAddedDate())
                .build();
    }

    @Override
    public WordDto requestToDto(WordRequest request) {
        return WordDto.builder()
                .englishWord(request.getEnglishWord())
                .turkishMeaning(request.getTurkishMeaning())
                .addedDate(request.getAddedDate() != null ? request.getAddedDate() : LocalDate.now())
                .build();
    }

    @Override
    public WordResponse toResponse(WordDto dto) {
        return WordResponse.builder()
                .id(dto.getId())
                .created(dto.getCreated())
                .modified(dto.getModified())
                .englishWord(dto.getEnglishWord())
                .turkishMeaning(dto.getTurkishMeaning())
                .addedDate(dto.getAddedDate())
                .build();
    }
}