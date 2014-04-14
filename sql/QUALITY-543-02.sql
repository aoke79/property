-- Create table
create table TF_QUAL_PILOT_APPINFOR_T_TWO
(
  ID                  VARCHAR2(36) not null,
  DETAILSID           VARCHAR2(36),
  AIRCRAFTTYPE        VARCHAR2(1),
  TOTAL               NUMBER(10),
  INSTRUCTION         NUMBER(10),
  SOLO                NUMBER(10),
  CAPTAIN_PIC         NUMBER(10),
  CAPTAIN_SPIC        NUMBER(10),
  TURN_TIME           NUMBER(10),
  CAPTAINTURN_PIC     NUMBER(10),
  CAPTAINTURN_SPIC    NUMBER(10),
  INSTRUMENT          NUMBER(10),
  INSTRUCTIN_NIGHT    NUMBER(10),
  NIGHT_UPDOWNNUM     NUMBER(10),
  NIGHT_PIC           NUMBER(10),
  NIGHT_SPIC          NUMBER(10),
  NIGHT_UPDOWNNUMPIC  NUMBER(10),
  NIGHT_UPDOWNNUMSPIC NUMBER(10),
  FLIGHT_NO           NUMBER(10),
  AERO_NO             NUMBER(10),
  GROUND_NO           NUMBER(10),
  POWERED_NO          NUMBER(10)
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
alter table TF_QUAL_PILOT_APPINFOR_T_TWO
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
alter table TF_QUAL_PILOT_APPINFOR_T_TWO
  add foreign key (DETAILSID)
  references TF_QUAL_DECLARA_PILOT (DETAILSID);
  
  
  
comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.DETAILSID is 
'�걨��Ա��ϸID';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.AIRCRAFTTYPE is 
'���������';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.TOTAL is 
'�ܷ���ʱ��';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.INSTRUCTION is 
'����ʱ��';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.SOLO is 
'����ʱ��';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.CAPTAIN_PIC is 
'����ʱ��PIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.CAPTAIN_SPIC is 
'����ʱ��SPIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.TURN_TIME is 
'ת��ʱ��';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.CAPTAINTURN_PIC is 
'����ת��PIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.CAPTAINTURN_SPIC is 
'����ת��SPIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.INSTRUMENT is 
'�Ǳ�ʱ��';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.INSTRUCTIN_NIGHT is 
'ҹ�����ʱ��';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.NIGHT_UPDOWNNUM is 
'ҹ���������';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.NIGHT_PIC is 
'����ҹ��ʱ��PIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.NIGHT_SPIC is 
'����ҹ��ʱSPIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.NIGHT_UPDOWNNUMPIC is 
'����ҹ���������PIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.NIGHT_UPDOWNNUMSPIC is 
'����ҹ���������SPIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.FLIGHT_NO is 
'���д���';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.AERO_NO is 
'����ǣ������';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.GROUND_NO is 
'����ǣ������';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.POWERED_NO is 
'������ɴ���';
