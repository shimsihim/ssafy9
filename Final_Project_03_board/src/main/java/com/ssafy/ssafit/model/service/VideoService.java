package com.ssafy.ssafit.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.Video;


public interface VideoService {

	
	//전체 게시글 가져오기 (모든 영상을 리스트로 불러오기)
	public List<Video> selectAll();
	
	//파트별 영상 불러오기
	public List<Video> selectPartly(String video_part);
	
	//하나의 영상 상세보기
	public Video selectOne(String video_id) throws SQLException;
	
	//검색조건
	public List<Video> searchByCondition(SearchCondition searchCondition);

}