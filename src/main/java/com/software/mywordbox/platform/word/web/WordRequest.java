package com.software.mywordbox.platform.word.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WordRequest {
    private String englishWord;
    private String turkishMeaning;
    private LocalDate addedDate; // null ise bugünün tarihi kullanılır
}
