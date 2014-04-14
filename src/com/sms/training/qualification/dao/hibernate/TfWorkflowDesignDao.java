package com.sms.training.qualification.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.hibernate.PublicDao;
import com.sms.training.qualification.bean.TfWorkflowDesign;
import com.sms.training.qualification.dao.ITfWorkflowDesignDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * TfWorkflowDesign entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see .TfWorkflowDesign
 * @author MyEclipse Persistence Tools
 */
@Repository("tfWorkflowDesignDao")
@Transactional
public class TfWorkflowDesignDao  extends PublicDao implements ITfWorkflowDesignDao {
	 

}