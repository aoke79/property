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
'申报人员明细ID';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.AIRCRAFTTYPE is 
'航空器类别';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.TOTAL is 
'总飞行时间';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.INSTRUCTION is 
'带飞时间';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.SOLO is 
'单飞时间';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.CAPTAIN_PIC is 
'机长时间PIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.CAPTAIN_SPIC is 
'机长时间SPIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.TURN_TIME is 
'转场时间';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.CAPTAINTURN_PIC is 
'机长转场PIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.CAPTAINTURN_SPIC is 
'机长转场SPIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.INSTRUMENT is 
'仪表时间';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.INSTRUCTIN_NIGHT is 
'夜间带飞时间';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.NIGHT_UPDOWNNUM is 
'夜间起落次数';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.NIGHT_PIC is 
'机长夜间时间PIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.NIGHT_SPIC is 
'机长夜间时SPIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.NIGHT_UPDOWNNUMPIC is 
'机长夜间起落次数PIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.NIGHT_UPDOWNNUMSPIC is 
'机长夜间起落次数SPIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.FLIGHT_NO is 
'飞行次数';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.AERO_NO is 
'空中牵引次数';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.GROUND_NO is 
'地面牵引次数';

comment on column TF_QUAL_PILOT_APPINFOR_T_TWO.POWERED_NO is 
'自行起飞次数';
