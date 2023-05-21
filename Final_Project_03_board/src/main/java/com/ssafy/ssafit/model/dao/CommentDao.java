package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.Comment;

public interface CommentDao {
	
	
	Comment selectOne(int comment_num);  // token 유효성 확인 위해서 불러오기
	void registComment(Comment comment);        // 댓글 등록
	List<Comment> selectComment(int post_num);  // 게시물에 대한 전체 코멘트 가져오기
	void updateComment(Comment comment);        // 댓글 수정
	void deleteComment(int comment_num);        // 댓글 삭제
	
	
}
