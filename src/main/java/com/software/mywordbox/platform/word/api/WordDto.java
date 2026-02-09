package com.software.mywordbox.platform.word.api;

import com.software.mywordbox.library.abstraction.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class WordDto extends AbstractDto {
    private String userId;
    private String englishWord;
    private String turkishMeaning;
    private LocalDate addedDate;
}
