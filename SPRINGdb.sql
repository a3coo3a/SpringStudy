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

select * from board where name like '%ȫ�浿%' and title = '�׽�Ʈ6';
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
from(
select rownum as rn,
       a.*
from (select * 
      from FREEBOARD
      order by bno desc) a)
where rn >40;