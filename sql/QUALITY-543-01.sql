-- Create table
create table TF_QUAL_PILOT_APPINFOR_T_ONE
(
  ID                  VARCHAR2(36) not null,
  ENDDT               DATE,
  DETAILSID           VARCHAR2(36),
  NIGHT_PIC           NUMBER(10),
  INSTRUCTIN_NIGHT    NUMBER(10),
  NIGHT_UPDOWNNUM     NUMBER(10),
  INSTRUMENT          NUMBER(10),
  CAPTAINTURN_SPIC    NUMBER(10),
  CAPTAINTURN_PIC     NUMBER(10),
  TURN_TIME           NUMBER(10),
  CAPTAIN_SPIC        NUMBER(10),
  CAPTAIN_PIC         NUMBER(10),
  SOLO                NUMBER(10),
  INSTRUCTION         NUMBER(10),
  ATRID               VARCHAR2(36),
  TOTAL               NUMBER(10),
  NIGHT_SPIC          NUMBER(10),
  NIGHT_UPDOWNNUMPIC  NUMBER(10),
  NIGHT_UPDOWNNUMSPIC NUMBER(10),
  REMARKS             VARCHAR2(60),
  STARTDT             DATE
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
alter table TF_QUAL_PILOT_APPINFOR_T_ONE
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
alter table TF_QUAL_PILOT_APPINFOR_T_ONE
  add foreign key (DETAILSID)
  references TF_QUAL_DECLARA_PILOT (DETAILSID);
alter table TF_QUAL_PILOT_APPINFOR_T_ONE
  add foreign key (ATRID)
  references BASE_AIRPLANTYPE (ID);


--ע�ͣ�
 
comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.ENDDT is 
'����ʱ��';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.DETAILSID is 
'�걨��Ա��ϸID';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.NIGHT_PIC is 
'����ҹ��ʱ��PIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.INSTRUCTIN_NIGHT is 
'ҹ�����ʱ��';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.NIGHT_UPDOWNNUM is 
'ҹ���������';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.INSTRUMENT is 
'�Ǳ�ʱ��';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.CAPTAINTURN_SPIC is 
'����ת��ʱ��SPIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.CAPTAINTURN_PIC is 
'����ת��ʱ��PIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.TURN_TIME is 
'ת������ʱ��';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.CAPTAIN_SPIC is 
'����ʱ��SPIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.CAPTAIN_PIC is 
'����ʱ��PIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.SOLO is 
'����ʱ��';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.INSTRUCTION is 
'����ʱ��';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.ATRID is 
'���ɻ���';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.TOTAL is 
'�ܷ���ʱ��';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.NIGHT_SPIC is 
'����ҹ��ʱ��SPIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.NIGHT_UPDOWNNUMPIC is 
'����ҹ���������PIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.NIGHT_UPDOWNNUMSPIC is 
'����ҹ���������SPIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.REMARKS is 
'��ע';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.STARTDT is 
'��ʼʱ��';