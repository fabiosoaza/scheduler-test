package com.example.scheduler.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "message")
public class SMS {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "mobile")
	private String mobile;
	
	@Column(name = "msg")
	private String msg;
	
	@Column(name = "external_key")
	private String externalId;
	
	@Column(name = "sender")
	private String sender;
	
	@Column(name = "schedule")
	@Temporal(TemporalType.TIMESTAMP)
	private Date schedule=new Date();
	
	public SMS() {		
	}

	public SMS(String mobile, String msg, String externalId, String sender, Date date) {
		super();
		this.mobile = mobile;
		this.msg = msg;
		this.externalId = externalId;
		this.sender = sender;
		this.schedule = date;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String id) {
		this.externalId = id;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public Date getDate() {
		return schedule;
	}
	public void setDate(Date date) {
		this.schedule = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "SMS [id=" + id + ", mobile=" + mobile + ", msg=" + msg + ", externalId=" + externalId + ", sender="
				+ sender + ", schedule=" + schedule + "]";
	}
	
	

	
	
	
	
	
	
}
