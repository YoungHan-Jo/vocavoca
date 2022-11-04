package com.hello.vocavoca.web.studyset.repository;

import com.hello.vocavoca.domain.member.Member;
import com.hello.vocavoca.web.studyset.form.StudySetListForm;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hello.vocavoca.domain.studyset.QStudySet.studySet;
import static com.hello.vocavoca.domain.word.QVoca.voca;

@Repository
@RequiredArgsConstructor
public class StudySetRepositoryImpl implements StudySetRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<StudySetListForm> findStudySetListForm(Pageable pageable, Member member) {

        List<StudySetListForm> result = queryFactory
                .select(Projections.constructor(StudySetListForm.class,
                        studySet.id,
                        studySet.title,
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(voca.count())
                                        .from(voca)
                                        .where(voca.studySet.eq(studySet)), "count"),
                        studySet.member.name
                ))
                .from(studySet)
                .where(memberEq(member))
                .orderBy(studySet.modifiedDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = queryFactory
                .select(studySet.count())
                .from(studySet)
                .where(memberEq(member))
                .fetchOne();

        return new PageImpl<>(result, pageable, totalCount);
    }

    private BooleanExpression memberEq(Member member) {
        return member != null ? studySet.member.eq(member) : null;
    }
}
