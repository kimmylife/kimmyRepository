package com.koreait.day1;

import com.koreait.day1.model.entity.TbExamBook;
import com.koreait.day1.repository.TbExamBookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


public class TbExamBookApiLogicServiceTest extends Day1ApplicationTests {

    @Autowired
    private TbExamBookRepository tbExamBookRepository;

    @Test
    public void create() {
        TbExamBook tbExamBook = new TbExamBook();
        tbExamBook.setBkTitle("책의 제목3");
        tbExamBook.setBkSummary("책의 내용이겠지3");
        tbExamBook.setBkPrice(10000);
        tbExamBook.setBkViewcnt(0);

        System.out.println(tbExamBook.getBkTitle()+"이 정상적으로 등록되었습니다");
        TbExamBook newTbExamBook = tbExamBookRepository.save(tbExamBook);

    }

    @Test
    public void read() {
        Optional<TbExamBook> tbExamBook = tbExamBookRepository.findByBkUid(2L);
        tbExamBook.ifPresent(
                selectTbExamBook -> {
                    selectTbExamBook.setBkViewcnt(selectTbExamBook.getBkViewcnt()+1);
                    tbExamBookRepository.save(selectTbExamBook);
                    System.out.println("--------------------------------------------");
                    System.out.println("id : " + selectTbExamBook.getBkUid());
                    System.out.println("제목 : " + selectTbExamBook.getBkTitle());
                    System.out.println("상세정보 : " + selectTbExamBook.getBkSummary());
                    System.out.println("가격 : " + selectTbExamBook.getBkPrice());
                    System.out.println("조회수 : " + selectTbExamBook.getBkViewcnt());
                    System.out.println("등록일자 : " + selectTbExamBook.getBkRegdate());
                    System.out.println("--------------------------------------------");
                }
        );
    }

    @Test
    void update() {
        Optional<TbExamBook> tbExamBook = tbExamBookRepository.findByBkUid(2L);
        tbExamBook.ifPresent(
                selectTbExamBook -> {
                    System.out.println("--------------------------------------------");
                    selectTbExamBook.setBkTitle("수정한 책 제목");
                    selectTbExamBook.setBkSummary("수정한 책 내용");
                    selectTbExamBook.setBkPrice(20000);
                    tbExamBookRepository.save(selectTbExamBook);
                    System.out.println(selectTbExamBook.getBkUid()+ "번 책이 수정됐습니다.");

                    System.out.println("--------------------------------------------");
                }
        );
    }

    @Test
    void delete() {
        Optional<TbExamBook> tbExamBook = tbExamBookRepository.findByBkUid(3L);
        System.out.println("--------------------------------------------");
        tbExamBook.ifPresent(
                selectTbExamBook -> {
                    tbExamBookRepository.delete(selectTbExamBook);
                }
        );
        System.out.println("삭제완료!");
        System.out.println("--------------------------------------------");
    }

    @Test
    void search() {
        List<TbExamBook> tbExamBooks = tbExamBookRepository.findAll();
        System.out.println("--------------------------------------------");
        tbExamBooks.forEach(
                tbExamBook -> {
                    System.out.println("---------------------------------" +
                            "\nid : " + tbExamBook.getBkUid()+
                            "\n제목 : " + tbExamBook.getBkTitle()+
                            "\n상세정보 : " + tbExamBook.getBkSummary()+
                            "\n가격 : " + tbExamBook.getBkPrice()+
                            "\n조회수 : " + tbExamBook.getBkViewcnt()+
                            "\n등록일자 : " + tbExamBook.getBkRegdate()
                    );
                    System.out.println("---------------------------------");

                }
        );

    }
}