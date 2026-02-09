package com.software.mywordbox.platform.word.api;

import com.software.mywordbox.library.abstraction.AbstractService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface WordService extends AbstractService<WordDto> {

    // Belirli tarihteki kelimeleri getir
    Page<WordDto> getWordsByDate(LocalDate date, Pageable pageable);

    // İki tarih aralığındaki kelimeleri getir
    Page<WordDto> getWordsByDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable);

    // Son 1 aya ait kelimeleri getir
    Page<WordDto> getWordsLastMonth(Pageable pageable);

}
