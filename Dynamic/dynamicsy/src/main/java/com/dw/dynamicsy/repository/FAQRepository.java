package com.dw.dynamic.repository;

import com.dw.dynamic.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FAQRepository extends JpaRepository<Board, String> {
}
