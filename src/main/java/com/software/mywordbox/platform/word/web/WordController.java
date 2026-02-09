package com.software.mywordbox.platform.word.web;

import com.software.mywordbox.library.abstraction.AbstractController;
import com.software.mywordbox.library.rest.PageResponse;
import com.software.mywordbox.library.rest.Response;
import com.software.mywordbox.platform.word.api.WordDto;
import com.software.mywordbox.platform.word.api.WordMapper;
import com.software.mywordbox.platform.word.api.WordService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/words")
public class WordController extends AbstractController<WordDto, WordRequest, WordResponse> {

    private final WordService wordService;
    private final WordMapper wordMapper;

    public WordController(WordService service, WordMapper mapper) {
        super(service, mapper);
        this.wordService = service;
        this.wordMapper = mapper;
    }

    // Belirli tarihteki kelimeleri getir
    @GetMapping("/date/{date}")
    public Response<PageResponse<WordResponse>> getWordsByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Pageable pageable) {

        Page<WordDto> words = wordService.getWordsByDate(date, pageable);
        Page<WordResponse> responses = words.map(wordMapper::toResponse);

        return respond(responses);
    }

    // İki tarih aralığındaki kelimeleri getir
    @GetMapping("/date-range")
    public Response<PageResponse<WordResponse>> getWordsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Pageable pageable) {

        Page<WordDto> words = wordService.getWordsByDateRange(startDate, endDate, pageable);
        Page<WordResponse> responses = words.map(wordMapper::toResponse);

        return respond(responses);
    }

    // Son 1 aya ait kelimeleri getir
    @GetMapping("/last-month")
    public Response<PageResponse<WordResponse>> getWordsLastMonth(Pageable pageable) {
        Page<WordDto> words = wordService.getWordsLastMonth(pageable);
        Page<WordResponse> responses = words.map(wordMapper::toResponse);

        return respond(responses);
    }
}
