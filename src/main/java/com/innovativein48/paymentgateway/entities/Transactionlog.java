package com.innovativein48.paymentgateway.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Transactionlog")
public class Transactionlog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long referenceid;
	
	private Long accountnumber;
	private String transdate;
	private Double tranlat;
	private Double tranlong;
	private Double devlat;
	private Double devlong;
	private String  trantype;
	private String status;
	private String reason;
	private Timestamp  timestamp;
	
	
	
	public Long getReferenceid() {
		return referenceid;
	}
	public void setReferenceid(Long referenceid) {
		this.referenceid = referenceid;
	}
	public Long getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(Long accountnumber) {
		this.accountnumber = accountnumber;
	}
	public String getTransdate() {
		return transdate;
	}
	public void setTransdate(String transdate) {
		this.transdate = transdate;
	}
	public Double getTranlat() {
		return tranlat;
	}
	public void setTranlat(Double tranlat) {
		this.tranlat = tranlat;
	}
	public Double getTranlong() {
		return tranlong;
	}
	public void setTranlong(Double tranlong) {
		this.tranlong = tranlong;
	}
	public Double getDevlat() {
		return devlat;
	}
	public void setDevlat(Double devlat) {
		this.devlat = devlat;
	}
	public Double getDevlong() {
		return devlong;
	}
	public void setDevlong(Double devlong) {
		this.devlong = devlong;
	}
	public String getTrantype() {
		return trantype;
	}
	public void setTrantype(String trantype) {
		this.trantype = trantype;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp  timestamp) {
		this.timestamp = timestamp;
	}
}
