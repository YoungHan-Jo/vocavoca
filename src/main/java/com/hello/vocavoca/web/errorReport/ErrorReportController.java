package com.hello.vocavoca.web.errorReport;

import com.hello.vocavoca.domain.errorReport.ErrorReport;
import com.hello.vocavoca.domain.errorReport.repository.ErrorReportRepository;
import com.hello.vocavoca.web.errorReport.form.ErrorReportAddForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
@Controller
public class ErrorReportController {

    private final ErrorReportRepository errorReportRepository;

    @PostMapping("/errorReport/add")
    public String add(@ModelAttribute("form") ErrorReportAddForm form) {

        ErrorReport errorReport = ErrorReport.builder()
                .errorPath(form.getErrorPath())
                .status(form.getStatus())
                .message(form.getMessage())
                .build();
        errorReportRepository.save(errorReport);

        return "error/sendReportAlert";
    }
}
