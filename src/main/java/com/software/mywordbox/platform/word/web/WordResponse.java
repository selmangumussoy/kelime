package com.software.mywordbox.platform.word.web;

import com.software.mywordbox.library.abstraction.AbstractResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class WordResponse extends AbstractResponse {
    private String englishWord;
    private String turkishMeaning;
    private LocalDate addedDate;
}
