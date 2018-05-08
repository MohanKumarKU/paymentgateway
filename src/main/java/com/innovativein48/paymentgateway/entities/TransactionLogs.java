package com.innovativein48.paymentgateway.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.innovativein48.paymentgateway.bean.Reason;
import com.innovativein48.paymentgateway.bean.Status;
import com.innovativein48.paymentgateway.bean.TransactionType;

@Entity
public class TransactionLogs {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long referenceId;
	
	private Long accountNumber;
	private String transDate;
	private String transactionSource;
	private String deviceId;
	private Double tranLat;
	private Double tranLong;
	private Double devLat;
	private Double devLong;
	private TransactionType  tranType;
	private String merchantID;
	private String merchantName;
	private Status status;
	private Reason reason;
	private String timeStamp;
	
	
	public Long getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(Long referenceId) {
		this.referenceId = referenceId;
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public String getTransactionSource() {
		return transactionSource;
	}
	public void setTransactionSource(String transactionSource) {
		this.transactionSource = transactionSource;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public Double getTranLat() {
		return tranLat;
	}
	public void setTranLat(Double tranLat) {
		this.tranLat = tranLat;
	}
	public Double getTranLong() {
		return tranLong;
	}
	public void setTranLong(Double tranLong) {
		this.tranLong = tranLong;
	}
	public Double getDevLat() {
		return devLat;
	}
	public void setDevLat(Double devLat) {
		this.devLat = devLat;
	}
	public Double getDevLong() {
		return devLong;
	}
	public void setDevLong(Double devLong) {
		this.devLong = devLong;
	}
	public TransactionType getTranType() {
		return tranType;
	}
	public void setTranType(TransactionType tranType) {
		this.tranType = tranType;
	}
	public String getMerchantID() {
		return merchantID;
	}
	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Reason getReason() {
		return reason;
	}
	public void setReason(Reason reason) {
		this.reason = reason;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
}
