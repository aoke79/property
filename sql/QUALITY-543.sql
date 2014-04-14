-- Create table
create table TF_QUAL_PILOT_TEACHER_FORM
(
  ID               VARCHAR2(36) not null,
  DETAILSID        VARCHAR2(36),
  AIRCRAFT_TYPE    VARCHAR2(50),
  APPLICATION_ITEM VARCHAR2(1),
  INFO_1           VARCHAR2(1),
  INFO_15A         VARCHAR2(1),
  INFO_15B         VARCHAR2(1),
  INFO_15C         VARCHAR2(1),
  INFO_15D         VARCHAR2(1),
  INFO_15E         VARCHAR2(1),
  INFO_2           VARCHAR2(36),
  INFO_22A         VARCHAR2(1),
  INFO_22B         VARCHAR2(1),
  INFO_22C         VARCHAR2(1),
  INFO_22D         VARCHAR2(1),
  INFO_23A         VARCHAR2(50),
  INFO_23B         DATE,
  INFO_23C         VARCHAR2(100),
  INFO_24A         VARCHAR2(1),
  INFO_24B         DATE,
  INFO_25A         VARCHAR2(50),
  INFO_25B         VARCHAR2(50),
  INFO_25C         VARCHAR2(50),
  INFO_26A         VARCHAR2(50),
  INFO_26B         DATE,
  INFO_26C         VARCHAR2(100),
  INFO_27A         VARCHAR2(1),
  INFO_27B         VARCHAR2(1),
  INFO_28A         VARCHAR2(1),
  INFO_28B         VARCHAR2(1),
  INFO_29A         VARCHAR2(100),
  INFO_29B         VARCHAR2(100),
  INFO_29C         VARCHAR2(100),
  INFO_29D         DATE,
  INFO_3           DATE,
  INFO_30          VARCHAR2(36),
  INFO_31          DATE,
  INFO_32          VARCHAR2(30),
  INFO_33          VARCHAR2(36),
  INFO_34          DATE,
  INFO_35          VARCHAR2(1),
  INFO_36          VARCHAR2(1),
  INFO_36_REAS     VARCHAR2(300),
  INFO_37          VARCHAR2(300),
  INFO_38          DATE,
  INFO_39A         DATE,
  INFO_39B         DATE,
  INFO_39C         DATE,
  INFO_4           VARCHAR2(1),
  INFO_40          VARCHAR2(36),
  INFO_41          VARCHAR2(36),
  INFO_42          VARCHAR2(36),
  INFO_43          VARCHAR2(36),
  INFO_44          DATE,
  INFO_45          VARCHAR2(30),
  INFO_46          VARCHAR2(36),
  INFO_47          VARCHAR2(1),
  INFO_47_REAS     VARCHAR2(300),
  INFO_48          VARCHAR2(30),
  INFO_49          DATE,
  INFO_5           VARCHAR2(1),
  INFO_50          VARCHAR2(30),
  INFO_51          DATE,
  INFO_52          VARCHAR2(30),
  INFO_53          DATE,
  INFO_54          VARCHAR2(1),
  INFO_54_REAS     VARCHAR2(30),
  INFO_55          VARCHAR2(30),
  INFO_56          DATE,
  INFO_57          VARCHAR2(30),
  INFO_58          DATE,
  INFO_59          VARCHAR2(30),
  INFO_60          DATE,
  INFO_61          VARCHAR2(30),
  INFO_61_OTHERS   VARCHAR2(300),
  INFO_A           VARCHAR2(1),
  INFO_B           VARCHAR2(1),
  INFO_C           VARCHAR2(1),
  INFO_D           VARCHAR2(1),
  INFO_GRADE_65    VARCHAR2(30),
  LICENE           VARCHAR2(1),
  MODIFIER         VARCHAR2(36),
  PILOTID          VARCHAR2(36)
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
-- Create/Recreate primary, unique and foreign key constraints 
alter table TF_QUAL_PILOT_TEACHER_FORM
  add primary key (ID)
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
alter table TF_QUAL_PILOT_TEACHER_FORM
  add foreign key (DETAILSID)
  references TF_QUAL_DECLARA_PILOT (DETAILSID);
  
  
comment on column TF_QUAL_PILOT_TEACHER_FORM.ID is 
'�����ID';

comment on column TF_QUAL_PILOT_TEACHER_FORM.DETAILSID is 
'�걨��Ա��ϸID';

comment on column TF_QUAL_PILOT_TEACHER_FORM.PILOTID is 
'����ԱID';

comment on column TF_QUAL_PILOT_TEACHER_FORM.LICENE is 
'��ʻԱִ��';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_1 is 
'�����еĺ�������ʻԱִ����ȼ�';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_15a is 
'15a';

comment on column TF_QUAL_PILOT_TEACHER_FORM.Aircraft_Type is 
'�ͱ�ȼ�';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_2 is 
'ִ�ձ��';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_3 is 
'�䷢����';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_4 is 
'��˵��д����';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_5 is 
'Ӣ�����ߵ�ͨ���ʸ�';

comment on column TF_QUAL_PILOT_TEACHER_FORM.APPLICATION_ITEM is 
'����';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_22a is 
'22a';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_22b is 
'22b';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_A is 
'A��ѧԭ��';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_30 is 
'������ǩ��';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_31 is 
'��������';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_32 is 
'���н�Աִ�ձ��';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_33 is 
'��Աǩ��';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_34 is 
'��������';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_35 is 
'��ѧ����';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_36 is 
'����������';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_37 is 
'���Եص�';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_39a is 
'����ʱ�����';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_39b is 
'����ʱ��ģ���';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_39c is 
'����ʱ�����';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_38 is 
'��������';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_40 is 
'����ִ�ջ�ȼ�';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_41 is 
'ʹ�õĺ�����';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_42 is 
'�������ǼǺ�';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_43 is 
'�ϸ�֤���';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_44 is 
'ί����������';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_45 is 
'����Աǩ��';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_46 is 
'���������';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_47 is 
'������';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_48 is 
'���Աǩ��';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_49 is 
'�������';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_50 is 
'����ְ�ܲ��Ÿ�����ǩ��';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_51 is 
'ǩ������';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_52 is 
'��ʱִ��������ǩ��';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_53 is 
'��������';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_54 is 
'ִ���Ƿ�䷢ִ��';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_54_REAS is 
'���䷢˵������';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_55 is 
'�񺽾�_���Աǩ��';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_56 is 
'�񺽾�_�������';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_57 is 
'�񺽾�_ִ�չ�����ǩ��';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_58 is 
'�񺽾�_ǩ������';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_59 is 
'�񺽾�_��ʽִ��������ǩ��';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_60 is 
'�񺽾�_����ʱ��';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_61 is 
'�����ļ�';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_61_OTHERS is 
'�����ļ�';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_GRADE_65 is 
'���Ƽ���';

comment on column TF_QUAL_PILOT_TEACHER_FORM.MODIFIER is 
'����޸���';
