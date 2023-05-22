package com.ssafy.ssafit.model.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.ssafy.ssafit.model.dto.Record_ex;

public interface RecordService {

	void registRecode(Record_ex record);
//	List<Record> selectAllRecord(String id);	//나의 운동기록 전체 불러오기 

	List<Record_ex> getRecords(Map map);

	List<Record_ex> getMyAllRecords(String record_ex_user_id);

	void updateRecord(Record_ex record); // 기록 수정

	void deleteRecord(int record_num);
}
