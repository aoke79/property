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


--注释：
 
comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.ENDDT is 
'结束时间';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.DETAILSID is 
'申报人员明细ID';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.NIGHT_PIC is 
'机长夜间时间PIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.INSTRUCTIN_NIGHT is 
'夜间带飞时间';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.NIGHT_UPDOWNNUM is 
'夜间起落次数';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.INSTRUMENT is 
'仪表时间';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.CAPTAINTURN_SPIC is 
'机长转场时间SPIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.CAPTAINTURN_PIC is 
'机长转场时间PIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.TURN_TIME is 
'转场单飞时间';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.CAPTAIN_SPIC is 
'机长时间SPIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.CAPTAIN_PIC is 
'机长时间PIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.SOLO is 
'单飞时间';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.INSTRUCTION is 
'带飞时间';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.ATRID is 
'所飞机型';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.TOTAL is 
'总飞行时间';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.NIGHT_SPIC is 
'机长夜间时间SPIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.NIGHT_UPDOWNNUMPIC is 
'机长夜间起落次数PIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.NIGHT_UPDOWNNUMSPIC is 
'机长夜间起落次数SPIC';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.REMARKS is 
'备注';

comment on column TF_QUAL_PILOT_APPINFOR_T_ONE.STARTDT is 
'开始时间';