

create table tb_exam_book (
    bk_uid number(7) primary key,
    bk_title VARCHAR2(20) not null,
    bk_summary VARCHAR2(30),
    bk_price number(7) default 0 constraint pk_price check(bk_price>=0),
    bk_viewcnt number(7) default 0 constraint pk_viewcnt check(bk_viewcnt>=0),
    bk_regdate date default sysdate
);

create sequence exam_book_seq
    increment by 1
    start with 1;
    
select * from tb_exam_book;