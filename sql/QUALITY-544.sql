-- Create table
create table TF_QUAL_PILOT_APPINFO_TEACHER
(
  INFORMATIONID    VARCHAR2(36) not null,
  DETAILSID        VARCHAR2(36),
  PILOTID          VARCHAR2(36),
  LICENSE_RAT      VARCHAR2(1),
  LICENSE_RAT_NO   VARCHAR2(100),
  LICENSE_NO       VARCHAR2(36),
  LICENSE_DATE     DATE,
  INFO_1           VARCHAR2(1),
  INFO_3           DATE,
  INFO_2           VARCHAR2(1),
  INFO_6           VARCHAR2(1),
  INFO_5_1         VARCHAR2(1),
  INFO_5_1_DATE    DATE,
  INFO_5_2         VARCHAR2(1),
  INFO_5_2_DATE    DATE,
  INFO_4           VARCHAR2(1),
  INFO_7           VARCHAR2(500),
  INFO_8           VARCHAR2(500),
  LICENSE_TYPE     VARCHAR2(1),
  INFO_11          VARCHAR2(36),
  INFO_12          VARCHAR2(36),
  INFO_13          DATE,
  INFO_14          VARCHAR2(1),
  INFO_15          VARCHAR2(36),
  INFO_16          VARCHAR2(50),
  INFO_17          DATE,
  INFO_18          VARCHAR2(50),
  INFO_19          DATE,
  INFO_20          VARCHAR2(10),
  INFO_21          VARCHAR2(1),
  INFO_21_DATE     DATE,
  INFO_22          VARCHAR2(1),
  INFO_22_DATE     DATE,
  INFO_23          VARCHAR2(1),
  INFO_23_DATE     DATE,
  INFO_24          VARCHAR2(50),
  INFO_25          VARCHAR2(100),
  INFO_26          VARCHAR2(500),
  INFO_27          VARCHAR2(36),
  INFO_28          DATE,
  INFO_29          VARCHAR2(36),
  INFO_30          DATE,
  INFO_31          VARCHAR2(36),
  INFO_32          DATE,
  INFO_33          VARCHAR2(1),
  INFO_34          VARCHAR2(500),
  INFO_35          VARCHAR2(36),
  INFO_36          DATE,
  INFO_37          VARCHAR2(36),
  INFO_38          DATE,
  INFO_39          VARCHAR2(36),
  INFO_40          DATE,
  INFO_41          VARCHAR2(30),
  INFO_GRADE_42    VARCHAR2(10),
  INFO_24_YN       VARCHAR2(30),
  LICENSE_SINGLE   VARCHAR2(10),
  LICENSE_GRADE_NO VARCHAR2(100),
  MODIFIER         VARCHAR2(36)
)
tablespace SMSDB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFORMATIONID
  is '�����ID';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.DETAILSID
  is '�걨��Ա��ϸID';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.PILOTID
  is '����ԱID';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.LICENSE_RAT
  is '�����еķɻ���ʻԱִ����ȼ�';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.LICENSE_RAT_NO
  is '�������ͱ�ȼ�����';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.LICENSE_NO
  is 'ִ�ձ��';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.LICENSE_DATE
  is '�䷢����';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_1
  is '�Ƿ����CAAC�䷢�����ϸ�֤';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_3
  is '���ϸ�����';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_2
  is '���ϸ�֤�ȼ�';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_6
  is 'Ӣ�����ߵ�ͨ��ʵ�����Ժϸ�';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_5_1
  is '½��ͨ�������Ƿ�ϸ�';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_5_1_DATE
  is '½��ͨ�����Ժϸ�_ͨ��ʱ��';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_5_2
  is '����רҵӢ�￼���Ƿ�ϸ�';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_5_2_DATE
  is '����רҵӢ�￼�Ժϸ�_ͨ��ʱ��';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_4
  is '��˵��д����';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_7
  is '���ɵ���';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_8
  is '���ɺ���';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.LICENSE_TYPE
  is '�����ͱ�ȼ����н�Աִ������';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_11
  is '�ͱ�ȼ�����';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_12
  is '������ǩ��';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_13
  is '��������';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_14
  is '�����Ƽ�ִ������';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_15
  is '�����Ƽ�_�ͱ�ȼ�����';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_16
  is '�����Ƽ�_�����ǩ��';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_17
  is '�����Ƽ�_�������';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_18
  is '�����Ƽ�_������ǩ��';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_19
  is '�����Ƽ�_������ǩ������';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_20
  is '���ڵ��������';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_21
  is '������ѵ�Ƿ�ͨ��';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_21_DATE
  is '������ѵ_����';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_22
  is 'ģ���ѵ���Ƿ�ͨ��';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_22_DATE
  is 'ģ���ѵ��_����';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_23
  is '���ͷ�����ѵ�Ƿ�ͨ��';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_23_DATE
  is '���ͷ�����ѵ_����';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_24
  is '���Ա_���н�Աִ�����';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_25
  is '���Ա_�ͱ�ȼ�';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_26
  is '���Ա_��ͬ��˵��';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_27
  is '���Աǩ��';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_28
  is '���Աǩ������';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_29
  is '���Ա_���Ÿ�����ǩ��';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_30
  is '���Ա_���Ÿ�����ǩ������';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_31
  is '���Ա_������ǩ��';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_32
  is '���Ա_������ǩ��_����';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_33
  is '���ܾ�_�Ƿ�ͬ��䷢ִ��';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_34
  is '���ܾ�_����˵��';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_35
  is '�ܾ�_���Աǩ��';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_36
  is '�ܾ�_�������';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_37
  is '�ܾ�_������ǩ��';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_38
  is '�ܾ�_ǩ������';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_39
  is '�ܾ�_������ǩ��';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_40
  is '�ܾ�_��������';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_41
  is '�����ļ�';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_GRADE_42
  is '���Ƽ���';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_24_YN
  is '���Ա_�Ƿ�ͬ��ǩ��';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.LICENSE_SINGLE
  is '�����еķɻ���ʻԱִ����ȼ��Ƿ񵥷�';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.LICENSE_GRADE_NO
  is '�����еķɻ���ʻԱ�ȼ�';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.MODIFIER
  is '����޸���';
-- Create/Recreate primary, unique and foreign key constraints 
alter table TF_QUAL_PILOT_APPINFO_TEACHER
  add constraint PK_ID primary key (INFORMATIONID)
  using index 
  tablespace SMSDB
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table TF_QUAL_PILOT_APPINFO_TEACHER
  add constraint FK_APPINFORN__DE_PILOT foreign key (DETAILSID)
  references TF_QUAL_DECLARA_PILOT (DETAILSID);
