package com.wpengine.model;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountStatus {
	
	@JsonProperty
	private String account_id;
	@JsonProperty
	private String status;
	@JsonProperty
	private String created_on;

	/**
	 * @return the account_id
	 */
	public String getAccount_id() {
		return account_id;
	}
	/**
	 * @param account_id the account_id to set
	 */
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the create_on
	 */
	public String getCreate_on() {
		return created_on;
	}
	/**
	 * @param create_on the create_on to set
	 */
	public void setCreate_on(String create_on) {
		this.created_on= create_on;
	}
	
}
