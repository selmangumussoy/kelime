package com.software.mywordbox.platform.word.impl;

import com.software.mywordbox.library.rest.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

@Entity
@Table(name = Word.TABLE)
@Data
@EqualsAndHashCode(callSuper = true)
public class Word extends AbstractEntity {

    public static final String TABLE = "words";
    private static final String COL_USER_ID = "user_id";
    private static final String COL_ENGLISH_WORD = "english_word";
    private static final String COL_TURKISH_MEANING = "turkish_meaning";
    private static final String COL_ADDED_DATE = "added_date";

    @Column(name = COL_USER_ID, nullable = false)
    private String userId;

    @Column(name = COL_ENGLISH_WORD, nullable = false)
    private String englishWord;

    @Column(name = COL_TURKISH_MEANING, nullable = false)
    private String turkishMeaning;

    @Column(name = COL_ADDED_DATE, nullable = false)
    private LocalDate addedDate;

    @PrePersist
    protected void setDefaultDate() {
        if (addedDate == null) {
            addedDate = LocalDate.now();
        }
    }
}