package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.VideoComment;

public interface VideoCommentService {
	
	List<VideoComment> selectVideoComment(String videocomment_video_id);
	
	void registVideoComment(VideoComment comment);
	
	void updateVideoComment(VideoComment comment);
	
	void deleteVideoComment(int id);
}
