package com.sms.training.qualification.dao.hibernate;

// default package

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.hibernate.PublicDao;

import com.sms.training.qualification.dao.ITfQualBaseTypeGroupDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * TfQualBaseTypeGroup entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see .TfQualBaseTypeGroup
 * @author MyEclipse Persistence Tools
 */
@Repository("tfQualBaseTypeGroupDAO")
@Transactional
public class TfQualBaseTypeGroupDAO  extends PublicDao implements ITfQualBaseTypeGroupDAO {
		

}