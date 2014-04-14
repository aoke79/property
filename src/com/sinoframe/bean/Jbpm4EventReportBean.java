package com.sinoframe.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * CmmUsernameTest entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Jbpm4EventReportBean implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = 8993955203450443368L;
    private String userName;
    private String deploymentId;
    private String processDefinitionId;
    private String processInstanceId;
    
    private String days;
    private String taskId;
    private String owner;
    private String reason;
    private String centent;
    
    private String result;
    private String eventReportType;
    // Constructors

    /** default constructor */
    public Jbpm4EventReportBean() {
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getEventReportType() {
		return eventReportType;
	}

	public void setEventReportType(String eventReportType) {
		this.eventReportType = eventReportType;
	}

	public String getCentent() {
		return centent;
	}

	public void setCentent(String centent) {
		this.centent = centent;
	}


    
}