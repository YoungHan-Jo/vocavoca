package com.hello.vocavoca.domain.member.repository;

import com.hello.vocavoca.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
