package com.hello.vocavoca.web.studyset.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VocaRepositoryImpl implements VocaRepositoryCustom{

    private final JPAQueryFactory queryFactory;
}
