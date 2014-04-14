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
'申请表ID';

comment on column TF_QUAL_PILOT_TEACHER_FORM.DETAILSID is 
'申报人员明细ID';

comment on column TF_QUAL_PILOT_TEACHER_FORM.PILOTID is 
'飞行员ID';

comment on column TF_QUAL_PILOT_TEACHER_FORM.LICENE is 
'驾驶员执照';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_1 is 
'所持有的航空器驾驶员执照与等级';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_15a is 
'15a';

comment on column TF_QUAL_PILOT_TEACHER_FORM.Aircraft_Type is 
'型别等级';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_2 is 
'执照编号';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_3 is 
'颁发日期';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_4 is 
'听说读写汉语';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_5 is 
'英语无线电通信资格';

comment on column TF_QUAL_PILOT_TEACHER_FORM.APPLICATION_ITEM is 
'申请';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_22a is 
'22a';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_22b is 
'22b';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_A is 
'A教学原理';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_30 is 
'申请人签字';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_31 is 
'申请日期';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_32 is 
'飞行教员执照编号';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_33 is 
'教员签字';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_34 is 
'期满日期';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_35 is 
'教学能力';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_36 is 
'工作单结论';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_37 is 
'考试地点';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_39a is 
'考试时间地面';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_39b is 
'考试时间模拟机';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_39c is 
'考试时间飞行';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_38 is 
'考试日期';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_40 is 
'被考执照或等级';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_41 is 
'使用的航空器';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_42 is 
'航空器登记号';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_43 is 
'合格证编号';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_44 is 
'委任期满日期';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_45 is 
'考试员签字';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_46 is 
'地区管理局';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_47 is 
'审查结论';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_48 is 
'监察员签字';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_49 is 
'审查日期';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_50 is 
'飞行职能部门负责人签字';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_51 is 
'签发日期';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_52 is 
'临时执照制作人签字';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_53 is 
'制作日期';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_54 is 
'执照是否颁发执照';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_54_REAS is 
'不颁发说明问题';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_55 is 
'民航局_监察员签字';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_56 is 
'民航局_审查日期';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_57 is 
'民航局_执照管理部门签字';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_58 is 
'民航局_签发日期';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_59 is 
'民航局_正式执照制作人签字';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_60 is 
'民航局_制作时间';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_61 is 
'所附文件';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_61_OTHERS is 
'其他文件';

comment on column TF_QUAL_PILOT_TEACHER_FORM.INFO_GRADE_65 is 
'限制级别';

comment on column TF_QUAL_PILOT_TEACHER_FORM.MODIFIER is 
'最后修改人';
