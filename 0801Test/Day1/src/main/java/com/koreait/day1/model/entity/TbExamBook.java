package com.koreait.day1.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(
        name="exam_book_seq",
        sequenceName = "exam_book_seq",
        initialValue = 1,
        allocationSize = 1
)
@Builder
@EntityListeners(AuditingEntityListener.class)
public class TbExamBook {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exam_book_seq")
    private Long bkUid;
    private String bkTitle;
    private String bkSummary;
    private Integer bkPrice;
    private Integer bkViewcnt;
    @CreatedDate
    private LocalDateTime bkRegdate;

}
