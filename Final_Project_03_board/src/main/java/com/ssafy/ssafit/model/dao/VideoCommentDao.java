package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.Comment;
import com.ssafy.ssafit.model.dto.VideoComment;

public interface VideoCommentDao {
	
	
	void registVideoComment(VideoComment comment);        // 댓글 등록
	List<VideoComment> selectVideoComment(String videocomment_video_id);  // 게시물에 대한 전체 코멘트 가져오기
	void updateVideoComment(VideoComment comment);        // 댓글 수정
	void deleteVideoComment(int videocomment_num);        // 댓글 삭제
	String getVideoCommentWriterId(int videocomment_num);
	
}
