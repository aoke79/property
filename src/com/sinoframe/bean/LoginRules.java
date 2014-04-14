package com.sinoframe.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 描述: 登录规则校验实体类
 * @author LUJIE
 * 
 */


@Entity
@Table(name="LOGIN_RULES_INFO")
public class LoginRules {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	private String id;
	
    //登录失败次数
	@Column(name="NUMBERS",length=5)
	private int numbers ;
	
	//时间类型   0 - 年  1-月   2-日    3-小时  4-分钟
	@Column(name="TIME_TYPE",length=2)
	private String timeType;
	@Transient
    private String timeT;

	public String getTimeT() {
		String t = "分钟";
		if("0".equals(this.timeType)){
			return "年";
		}
		if("1".equals(this.timeType)){
			return "月";
		}
		if("2".equals(this.timeType)){
			return "天";
		}
		if("3".equals(this.timeType)){
			return "小时";
		}
		return t;
	}
	@Transient
	private String exT;
	public String getExT() {
		String e = "";
		if(this.ex.equals("Y")){
			e = "执行";
		}else{
			e = "未执行";
		}
		return e;
	}

	public void setExT(String exT) {
		this.exT = exT;
	}

	public void setTimeT(String timeT) {
		this.timeT = timeT;
	}

	//登录失败之后 多久才能登录
	@Column(name="TIMES",length=20)
	private int times ;
	
	//是否执行登录校验规则
	//Y -执行    N-不执行
	@Column(name="EX",length=2)
	private String ex = "Y";
	
    //该规则是否有效
	//0- 有效   1-无效    一个有效其他均无效
	@Column(name="STATUS",length=2)
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public int getNumbers() {
		return numbers;
	}

	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public String getEx() {
		return ex;
	}

	public void setEx(String ex) {
		this.ex = ex;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
}
