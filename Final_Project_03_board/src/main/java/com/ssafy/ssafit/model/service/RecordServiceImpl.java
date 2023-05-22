package com.ssafy.ssafit.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.RecordDao;
import com.ssafy.ssafit.model.dto.Record_ex;


@Service
public class RecordServiceImpl implements RecordService{

	
	RecordDao recordDao;
	
	@Autowired
	public void setDao(RecordDao recordDao) {
		this.recordDao = recordDao;
	}

	@Override
	public List<Record_ex> getRecords(Map map) {
		// TODO Auto-generated method stub
		return recordDao.getRecords(map);
	}

	@Override
	public List<Record_ex> getMyAllRecords(String record_ex_user_id) {
		// TODO Auto-generated method stub
		return recordDao.getMyAllRecords(record_ex_user_id);
	}

	@Override
	public void registRecode(Record_ex record) {
		recordDao.registRecode(record);		
	}

	@Override
	public void updateRecord(Record_ex record) {
		// TODO Auto-generated method stub
		recordDao.updateRecord(record);
	}

	@Override
	public void deleteRecord(int record_num) {
		// TODO Auto-generated method stub
		recordDao.deleteRecord(record_num);
	}

	

	
	
}
