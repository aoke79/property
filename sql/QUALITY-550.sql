
/* ADD BY licumn ,2013-05-07; create a table :TF_QUAL_COPILOT_CHECKLIST; */
-- Create table
create table TF_QUAL_COPILOT_CHECKLIST
(
  ID                        VARCHAR2(36) not null,
  DETAILS_ID                VARCHAR2(36),
  RUN_BASE                  VARCHAR2(60),
  CHECK_DATE                DATE,
  CHECK_LOCATION            VARCHAR2(60),
  CHECK_DEVICE              VARCHAR2(3),
  AIRCRAFT_MODEL            VARCHAR2(20),
  REGIST_CODE               VARCHAR2(10),
  SIMULATOR_CERTIFICATE_NO  VARCHAR2(20),
  CHECK_ITEM11              VARCHAR2(1),
  CHECK_ITEM12              VARCHAR2(1),
  CHECK_ITEM13              VARCHAR2(1),
  CHECK_ITEM14              VARCHAR2(1),
  CHECK_ITEM15              VARCHAR2(1),
  CHECK_ITEM2               VARCHAR2(1),
  CHECK_ITEM3               VARCHAR2(1),
  CHECK_ITEM4               VARCHAR2(1),
  EXAMINER_COMMENTS         VARCHAR2(150),
  EXAMINER_RESULT           VARCHAR2(1),
  EXAMINER_CERTIFICATE_NO   VARCHAR2(15),
  EXPIRY_DATE               DATE,
  EXAMINER_SIGNATURE        VARCHAR2(36),
  EXAMINER_SIGN_DATE        DATE,
  AREA_MANAGEMENT_BUREAU    VARCHAR2(2),
  SUPERVISORS_REVIEW_RESULT VARCHAR2(1),
  SUPERVISORS_SIGNATURE     VARCHAR2(36),
  SUPERVISORS_SIGN_DATE     DATE
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
comment on column TF_QUAL_COPILOT_CHECKLIST.ID
  is 'ID';
comment on column TF_QUAL_COPILOT_CHECKLIST.DETAILS_ID
  is '申报人员明细ID';
comment on column TF_QUAL_COPILOT_CHECKLIST.RUN_BASE
  is '运行基地';
comment on column TF_QUAL_COPILOT_CHECKLIST.CHECK_DATE
  is '检查日期';
comment on column TF_QUAL_COPILOT_CHECKLIST.CHECK_LOCATION
  is '检查地点';
comment on column TF_QUAL_COPILOT_CHECKLIST.CHECK_DEVICE
  is '检查设备';
comment on column TF_QUAL_COPILOT_CHECKLIST.AIRCRAFT_MODEL
  is '航空器型号';
comment on column TF_QUAL_COPILOT_CHECKLIST.REGIST_CODE
  is '飞机注册号';
comment on column TF_QUAL_COPILOT_CHECKLIST.SIMULATOR_CERTIFICATE_NO
  is 'CAAC模拟机合格证编号';
comment on column TF_QUAL_COPILOT_CHECKLIST.CHECK_ITEM11
  is '熟悉发动机、设备和系统操作程序';
comment on column TF_QUAL_COPILOT_CHECKLIST.CHECK_ITEM12
  is '熟悉性能和限制';
comment on column TF_QUAL_COPILOT_CHECKLIST.CHECK_ITEM13
  is '熟悉正常、非正常和应急操作程序';
comment on column TF_QUAL_COPILOT_CHECKLIST.CHECK_ITEM14
  is '熟悉经批准的运行手册';
comment on column TF_QUAL_COPILOT_CHECKLIST.CHECK_ITEM15
  is '熟悉标牌与标志';
comment on column TF_QUAL_COPILOT_CHECKLIST.CHECK_ITEM2
  is '独立操纵航空器完成起飞、着陆';
comment on column TF_QUAL_COPILOT_CHECKLIST.CHECK_ITEM3
  is '发动机停车后的处置程序';
comment on column TF_QUAL_COPILOT_CHECKLIST.CHECK_ITEM4
  is '机组资源管理训练';
comment on column TF_QUAL_COPILOT_CHECKLIST.EXAMINER_COMMENTS
  is '考试员评语';
comment on column TF_QUAL_COPILOT_CHECKLIST.EXAMINER_RESULT
  is '结论';
comment on column TF_QUAL_COPILOT_CHECKLIST.EXAMINER_CERTIFICATE_NO
  is '考试员合格证编号';
comment on column TF_QUAL_COPILOT_CHECKLIST.EXPIRY_DATE
  is '期满日期';
comment on column TF_QUAL_COPILOT_CHECKLIST.EXAMINER_SIGNATURE
  is '考试员签字';
comment on column TF_QUAL_COPILOT_CHECKLIST.EXAMINER_SIGN_DATE
  is '考试员签字日期';
comment on column TF_QUAL_COPILOT_CHECKLIST.AREA_MANAGEMENT_BUREAU
  is '所在地区管理局';
comment on column TF_QUAL_COPILOT_CHECKLIST.SUPERVISORS_REVIEW_RESULT
  is '监察员审查意见';
comment on column TF_QUAL_COPILOT_CHECKLIST.SUPERVISORS_SIGNATURE
  is '监察员签字';
comment on column TF_QUAL_COPILOT_CHECKLIST.SUPERVISORS_SIGN_DATE
  is '监察员签字日期';
-- Create/Recreate primary, unique and foreign key constraints 
alter table TF_QUAL_COPILOT_CHECKLIST
  add constraint PK_CHECKLISTID primary key (ID)
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
alter table TF_QUAL_COPILOT_CHECKLIST
  add constraint FK_CHECKLIST_REF_DETAILS foreign key (DETAILS_ID)
  references TF_QUAL_DECLARA_PILOT (DETAILSID);

commit;
