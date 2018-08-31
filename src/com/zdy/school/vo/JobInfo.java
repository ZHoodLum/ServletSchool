package com.zdy.school.vo;

import java.sql.Date;

/**
 * @author Psyduck
 * JobInfo 招聘信息表
 * 用于存放招聘信息的相关信息
 */
public class JobInfo {
	private int job_id;
	private String jbo_info;
	private String job_position;
	private Date job_date;
	private String enterprise_name;
	private String e_check;
	private String wage;
	public int getJob_id() {
		return job_id;
	}
	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}
	public String getJbo_info() {
		return jbo_info;
	}
	public void setJbo_info(String jbo_info) {
		this.jbo_info = jbo_info;
	}
	public String getJob_position() {
		return job_position;
	}
	public void setJob_position(String job_position) {
		this.job_position = job_position;
	}
	public Date getJob_date() {
		return job_date;
	}
	public void setJob_date(Date job_date) {
		this.job_date = job_date;
	}
	public String getEnterprise_name() {
		return enterprise_name;
	}
	public void setEnterprise_name(String enterprise_name) {
		this.enterprise_name = enterprise_name;
	}
	public String getE_check() {
		return e_check;
	}
	public void setE_check(String e_check) {
		this.e_check = e_check;
	}
	public String getWage() {
		return wage;
	}
	public void setWage(String wage) {
		this.wage = wage;
	}
	
}
