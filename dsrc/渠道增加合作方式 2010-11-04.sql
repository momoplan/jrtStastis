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
  is '������ʽ��';
-- Add comments to the columns 
comment on column COOPERAT.ID
  is '����';
comment on column COOPERAT.CHANNL_ID
  is '�������';
comment on column COOPERAT.COOPERAT_TYPE
  is '������ʽ ';
comment on column COOPERAT.COUNT
  is '�������(Ԫ)';
comment on column COOPERAT.COUNT_TYPE
  is '���㷽ʽ(1). Ԫ/�� 2.�ٷֱ�  ';







-- ��������ʱ��Ҫ�����Ӻ�����ʽ����������ǵ���չ�ԣ���������ʽ�������Ϣ����չ��һ����
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
  is '������ʽ��';
-- Add comments to the columns 
comment on column TCOOPERAT.ID
  is '����';
comment on column TCOOPERAT.CHANNL_ID
  is '������ţ�id��';
comment on column TCOOPERAT.COOPERAT_TYPE
  is '������ʽ';
comment on column TCOOPERAT.COUNT_TYPE
  is '���㷽ʽ(Ԫ/��,�ٷֱȣ�Ԫ/��)��';
comment on column TCOOPERAT.COUNT
  is '�������(Ԫ)';
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


---��������
create sequence TCOOPERAT_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;