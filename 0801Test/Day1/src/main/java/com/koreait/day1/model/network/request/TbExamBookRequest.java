package com.koreait.day1.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TbExamBookRequest {
    private Long bkUid;
    private String bkTitle;
    private String bkSummary;
    private Integer bkPrice;
    private Integer bkViewcnt;
    private LocalDateTime bkRegdate;
}
