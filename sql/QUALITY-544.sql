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
  is '申请表ID';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.DETAILSID
  is '申报人员明细ID';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.PILOTID
  is '飞行员ID';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.LICENSE_RAT
  is '所持有的飞机驾驶员执照与等级';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.LICENSE_RAT_NO
  is '航空器型别等级代码';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.LICENSE_NO
  is '执照编号';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.LICENSE_DATE
  is '颁发日期';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_1
  is '是否持有CAAC颁发的体检合格证';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_3
  is '体检合格日期';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_2
  is '体检合格证等级';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_6
  is '英语无线电通信实践考试合格';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_5_1
  is '陆空通话考试是否合格';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_5_1_DATE
  is '陆空通话考试合格_通过时间';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_5_2
  is '飞行专业英语考试是否合格';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_5_2_DATE
  is '飞行专业英语考试合格_通过时间';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_4
  is '听说读写汉语';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_7
  is '单飞地区';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_8
  is '单飞航线';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.LICENSE_TYPE
  is '申请型别等级飞行教员执照种类';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_11
  is '型别等级代码';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_12
  is '申请人签名';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_13
  is '申请日期';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_14
  is '部门推荐执照种类';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_15
  is '部门推荐_型别等级代码';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_16
  is '部门推荐_审核人签字';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_17
  is '部门推荐_审核日期';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_18
  is '部门推荐_负责人签字';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_19
  is '部门推荐_负责人签字日期';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_20
  is '所在地区管理局';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_21
  is '理论培训是否通过';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_21_DATE
  is '理论培训_日期';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_22
  is '模拟机训练是否通过';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_22_DATE
  is '模拟机训练_日期';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_23
  is '机型飞行培训是否通过';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_23_DATE
  is '机型飞行培训_日期';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_24
  is '监察员_飞行教员执照类别';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_25
  is '监察员_型别等级';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_26
  is '监察员_不同意说明';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_27
  is '监察员签字';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_28
  is '监察员签字日期';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_29
  is '监察员_部门负责人签字';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_30
  is '监察员_部门负责人签字日期';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_31
  is '监察员_制作人签字';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_32
  is '监察员_制作人签字_日期';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_33
  is '民航总局_是否同意颁发执照';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_34
  is '民航总局_问题说明';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_35
  is '总局_监察员签字';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_36
  is '总局_审核日期';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_37
  is '总局_负责人签字';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_38
  is '总局_签发日期';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_39
  is '总局_制作人签字';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_40
  is '总局_制作日期';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_41
  is '所附文件';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_GRADE_42
  is '限制级别';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.INFO_24_YN
  is '检察员_是否同意签发';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.LICENSE_SINGLE
  is '所持有的飞机驾驶员执照与等级是否单发';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.LICENSE_GRADE_NO
  is '所持有的飞机驾驶员等级';
comment on column TF_QUAL_PILOT_APPINFO_TEACHER.MODIFIER
  is '最后修改人';
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
