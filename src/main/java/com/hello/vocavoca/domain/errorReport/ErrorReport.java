package com.hello.vocavoca.domain.errorReport;

import com.hello.vocavoca.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Builder
@Entity
public class ErrorReport extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "error_report_id")
    private Long id;

    private String errorPath;

    private String message;

}
