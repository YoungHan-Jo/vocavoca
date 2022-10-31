package com.hello.vocavoca.domain.word.repository;

import com.hello.vocavoca.domain.word.Voca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VocaRepository extends JpaRepository<Voca, Long> {
}
