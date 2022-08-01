package com.koreait.day1.repository;

import com.koreait.day1.model.entity.TbExamBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TbExamBookRepository extends JpaRepository<TbExamBook, Long> {
    Optional<TbExamBook> findByBkUid(Long bkUid);
}
