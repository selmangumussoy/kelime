package com.software.mywordbox.platform.word.impl;


import com.software.mywordbox.library.abstraction.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface WordRepository extends AbstractRepository<Word> {

    // Pageable ile kullanıcının tüm kelimelerini getir
    Page<Word> findByUserId(String userId, Pageable pageable);

    // Belirli tarihteki kelimeleri pageable ile getir
    Page<Word> findByUserIdAndAddedDate(String userId, LocalDate date, Pageable pageable);

    // İki tarih aralığındaki kelimeleri pageable ile getir
    Page<Word> findByUserIdAndAddedDateBetween(String userId, LocalDate startDate, LocalDate endDate, Pageable pageable);
}
