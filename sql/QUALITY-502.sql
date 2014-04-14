/* add by licumn ,2013-04-24;*/
alter table tf_qual_inspection_train add modifier varchar2(36);
comment on column TF_QUAL_INSPECTION_TRAIN.modifier is '最后修改人';

commit;