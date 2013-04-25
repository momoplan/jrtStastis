-- Create table

-- 
create table COOPERAT
(
  ID            NUMBER(32)   not null,
  CHANNL_ID     VARCHAR2(20) not null,
  COOPERAT_TYPE VARCHAR2(20) not null,
  COUNT         NUMBER(32,2) not null,
  COUNT_TYPE    VARCHAR2(20) not null
)

-- Add comments to the table 
comment on table COOPERAT
  is '合作方式表';
-- Add comments to the columns 
comment on column COOPERAT.ID
  is '主键';
comment on column COOPERAT.CHANNL_ID
  is '渠道编号';
comment on column COOPERAT.COOPERAT_TYPE
  is '合作方式 ';
comment on column COOPERAT.COUNT
  is '合作金额(元)';
comment on column COOPERAT.COUNT_TYPE
  is '计算方式(1). 元/月 2.百分比  ';







-- 新增渠道时，要求增加合作方式与合作金额。考虑到扩展性，将合作方式及相关信息，扩展成一个表。
-- Create table
create table TCOOPERAT
(
  ID            NUMBER(32) not null,
  CHANNL_ID     NUMBER(32) not null,
  COOPERAT_TYPE VARCHAR2(20),
  COUNT_TYPE    VARCHAR2(20),
  COUNT         NUMBER(32,3)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table TCOOPERAT
  is '合作方式表';
-- Add comments to the columns 
comment on column TCOOPERAT.ID
  is '主键';
comment on column TCOOPERAT.CHANNL_ID
  is '渠道编号（id）';
comment on column TCOOPERAT.COOPERAT_TYPE
  is '合作方式';
comment on column TCOOPERAT.COUNT_TYPE
  is '计算方式(元/个,百分比，元/月)等';
comment on column TCOOPERAT.COUNT
  is '合作金额(元)';
-- Create/Recreate primary, unique and foreign key constraints 
alter table TCOOPERAT
  add constraint COOPERAT primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );


---创建序列
create sequence TCOOPERAT_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;