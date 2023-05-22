package com.ssafy.ssafit.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Record_ex;
import com.ssafy.ssafit.model.service.RecordService;
import com.ssafy.ssafit.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ssafit/record")
@Api(tags = "댓글 컨트롤러")

public class RecordController {

	@Autowired
	RecordService recordService;

	@Autowired
	private JwtUtil jwtUtil;
	
	
	
	

	@GetMapping("/{date}/{token}")
	@ApiOperation(value = "해당 날짜의 기록 불러오기")
	public ResponseEntity<?> setDayRecord(@PathVariable Date date,@PathVariable String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		
		String user_id = jwtUtil.parse(token);
		
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("record_ex_date", date);
		map.put("record_ex_user_id", user_id);
		
		
		return new ResponseEntity<List<Record_ex>>(recordService.getRecords(map),HttpStatus.OK);
	}

	
	
	
	@GetMapping("/{token}") // 유저의 전체 운동기록 불러오기
	@ApiOperation(value = "전체 운동기록 불러오기")
	public ResponseEntity<?> setMyRecords(@PathVariable String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		
		String user_id = jwtUtil.parse(token);
		
		List<Record_ex> list = recordService.getMyAllRecords(user_id);
		System.out.println(list.get(0).getRecord_ex_date());
		
		return new ResponseEntity<List<Record_ex>>(recordService.getMyAllRecords(user_id),HttpStatus.OK);
	}
	@PostMapping("/submit")
	@ApiOperation(value = "운동기록 등록")
	public ResponseEntity<?> submitRecord(@RequestBody HashMap<String, Object> map) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException  {

		Record_ex record = new Record_ex();
		record.setRecord_ex_date(java.sql.Date.valueOf((String) map.get("record_ex_date")));
		record.setRecord_ex_memo((String) map.get("record_ex_memo"));
		record.setRecord_ex_time(Integer.parseInt((String) map.get("record_ex_time")));
		record.setRecord_part((String) map.get("record_part"));
		String id = jwtUtil.parse((String) map.get("token"));
		record.setRecord_ex_user_id(id);
		recordService.registRecode(record);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/update")
	@ApiOperation(value = "기록수정", notes = "기록 수정하기 ")
	public ResponseEntity<?> updateComment(@RequestBody HashMap<String, Object> map)  {

		
		Record_ex record = new Record_ex();
		record.setRecord_ex_date(java.sql.Date.valueOf((String) map.get("record_ex_date")));
		record.setRecord_ex_memo((String) map.get("record_ex_memo"));
		record.setRecord_part((String) map.get("record_part"));
		record.setRecord_ex_time((int) map.get("record_ex_time"));
		record.setRecord_ex_num((int) map.get("record_ex_num"));
		
		recordService.updateRecord(record);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/remove/{record_ex_num}")
	@ApiOperation(value = "기록 삭제", notes = "기록 삭제하기")
	public ResponseEntity<?> updateComment(@PathVariable int record_ex_num) {

		
		recordService.deleteRecord(record_ex_num);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
