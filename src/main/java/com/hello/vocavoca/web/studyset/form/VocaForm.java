package com.hello.vocavoca.web.studyset.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VocaForm {

    private String word;
    private String meaning;
}
