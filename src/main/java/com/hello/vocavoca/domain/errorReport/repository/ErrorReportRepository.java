package com.hello.vocavoca.domain.errorReport.repository;

import com.hello.vocavoca.domain.errorReport.ErrorReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorReportRepository extends JpaRepository<ErrorReport, Long> {
}
