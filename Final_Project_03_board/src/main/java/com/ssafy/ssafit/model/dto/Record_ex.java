package com.ssafy.ssafit.model.dto;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Record_ex {

	private int record_ex_num;
	private String record_ex_user_id;
	private String record_part;
	private String record_ex_memo;
	private int record_ex_time;
	private Date record_ex_date;

	public Record_ex() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Record_ex(int record_ex_num, String record_ex_user_id, String record_part, String record_ex_memo,
			int record_ex_time, Date record_ex_date) {
		super();
		this.record_ex_num = record_ex_num;
		this.record_ex_user_id = record_ex_user_id;
		this.record_part = record_part;
		this.record_ex_memo = record_ex_memo;
		this.record_ex_time = record_ex_time;
		this.record_ex_date = record_ex_date;
	}

	public int getRecord_ex_num() {
		return record_ex_num;
	}

	public void setRecord_ex_num(int record_ex_num) {
		this.record_ex_num = record_ex_num;
	}

	public String getRecord_ex_user_id() {
		return record_ex_user_id;
	}

	public void setRecord_ex_user_id(String record_ex_user_id) {
		this.record_ex_user_id = record_ex_user_id;
	}

	public String getRecord_part() {
		return record_part;
	}

	public void setRecord_part(String record_part) {
		this.record_part = record_part;
	}

	public String getRecord_ex_memo() {
		return record_ex_memo;
	}

	public void setRecord_ex_memo(String record_ex_memo) {
		this.record_ex_memo = record_ex_memo;
	}

	public int getRecord_ex_time() {
		return record_ex_time;
	}

	public void setRecord_ex_time(int record_ex_time) {
		this.record_ex_time = record_ex_time;
	}

	public Date getRecord_ex_date() {
		return record_ex_date;
	}

	public void setRecord_ex_date(Date record_ex_date) {
		this.record_ex_date = record_ex_date;
	}

	@Override
	public String toString() {
		return "Record_ex [record_ex_num=" + record_ex_num + ", record_ex_user_id=" + record_ex_user_id
				+ ", record_part=" + record_part + ", record_ex_memo=" + record_ex_memo + ", record_ex_time="
				+ record_ex_time + ", record_ex_date=" + record_ex_date + "]";
	}

}
