package com.hello.vocavoca.web.errorReport.form;

import lombok.Data;

@Data
public class ErrorReportAddForm {
    private String errorPath;
    private String status;
    private String message;
}
