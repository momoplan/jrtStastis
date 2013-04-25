

-- Create table
create table JRTSTATIS.PICTURERINGCOUNT
(
  ID        NUMBER(10) not null,
  PID       VARCHAR2(30) not null,
  TYPE      VARCHAR2(10),
  DOWNCOUNT VARCHAR2(20) not null,
  DATETIME  TIMESTAMP(6),
  PNAME     VARCHAR2(200)
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table JRTSTATIS.PICTURERINGCOUNT
  add primary key (ID)
  using index ;
create sequence JRTSTATIS.pictureringcount_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;