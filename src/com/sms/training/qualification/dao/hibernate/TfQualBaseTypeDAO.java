package com.sms.training.qualification.dao.hibernate;
// default package

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.hibernate.PublicDao;
import com.sms.training.qualification.dao.ITfQualBaseTypeDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * TfQualBaseType entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see .TfQualBaseType
 * @author MyEclipse Persistence Tools
 */
@Repository("tfQualBaseTypeDAO")
@Transactional
public class TfQualBaseTypeDAO  extends PublicDao implements ITfQualBaseTypeDAO {

	

}