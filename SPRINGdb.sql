--SPRING/SPRING

CREATE TABLE SCORE(
    NUM NUMBER(10),
    NAME VARCHAR2(30),
    KOR VARCHAR2(30),
    ENG VARCHAR2(30),
    MATH VARCHAR2(30)
);

ALTER TABLE SCORE ADD CONSTRAINT SCORE_PK PRIMARY KEY (NUM);
DESC SCORE;
CREATE SEQUENCE SCORE_SEQ START WITH 1
  INCREMENT BY 1 NOCACHE;
  
select * from score;

CREATE TABLE BOARD(
    NUM NUMBER(10) PRIMARY KEY,
    NAME VARCHAR2(30),
    TITLE VARCHAR2(100),
    CONTENT VARCHAR2(200)
);
DESC BOARD;
CREATE SEQUENCE BOARD_SEQ START WITH 1 INCREMENT BY 1 NOCACHE;
select * from board;

select sysdate from dual;

select * from board where name like '%홍길동%' and title = '테스트6';
drop table freeboard;

create table freeboard(
    bno number(10),
    title VARCHAR2(200) not null,
    writer VARCHAR2(50) not null,
    content VARCHAR2(2000),
    regdate date DEFAULT sysdate,
    updatedate date DEFAULT sysdate
);
alter table freeboard add CONSTRAINT freeboard_pk primary key (bno);
create SEQUENCE freeboard_seq INCREMENT by 1 START WITH 1 NOCACHE;

select * from freeboard;

select *
from(select rownum as rn,
            a.*
     from (select * 
           from FREEBOARD
           order by bno desc) a)
where rn > 40 and rn <= 50;.

create table FREEREPLY(
    bno number(10),   -- 글번호 FK
    rno number(10) primary key, -- 댓글번호 PK
    reply varchar2(1000),  -- 내용
    replyId varchar2(50), -- 글 작성자 (writer)
    replyPw varchar2(50), -- 비밀번호
    replydate date default sysdate,  -- 등록일
    updatedate date default sysdate  -- 수정일
);

create sequence freereply_seq start with 1 increment by 1 nocache;

select * from freereply;

select * from FREEREPLY where bno = 299;

select count(*) from FREEREPLY where rno = 1 and replyPw = 'aaa';
commit;

declare
    var1 number := 1;
begin
    while var1 <= 100
    loop 
        insert into freereply values(299, freereply_seq.nextval, 'test','admin','1234',sysdate,sysdate);
        var1 := var1 + 1;
    end loop;
    commit;
end;

select *
from(
    select rownum as rn, 
           a.*
    from(
        select *
        from freereply
        where bno = 299
        order by rno desc)a
) where rn > 20 and rn <= 40;


create table users(
    userId VARCHAR2(50) not null,
    userPw varchar2(50) not null,
    userName varchar2(50) not null,
    userPhone1 VARCHAR2(50),
    userPhone2 VARCHAR2(50),
    userEmail1 VARCHAR2(50),
    userEmail2 VARCHAR2(50),
    addrBasic VARCHAR2(300),
    addrDetail VARCHAR2(300),
    addrZipNum VARCHAR2(50),
    regdate date default sysdate
);

alter table users add constraint userid_pk primary key (userId);

select * from users;

insert into users(userId, userPw, userName,userPhone1,userPhone2 ) values(); 