package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ssafit.model.dao.CommentDao;
import com.ssafy.ssafit.model.dao.VideoCommentDao;
import com.ssafy.ssafit.model.dto.Comment;
import com.ssafy.ssafit.model.dto.VideoComment;

@Service
public class VideoCommentServcieImpl implements VideoCommentService {

	@Autowired
	private VideoCommentDao videoCommentDao;



	@Override
	public List<VideoComment> selectVideoComment(String videocomment_video_id) {
		// TODO Auto-generated method stub
		return videoCommentDao.selectVideoComment(videocomment_video_id);
	}

	@Transactional
	@Override
	public void registVideoComment(VideoComment comment) {
		// TODO Auto-generated method stub
		videoCommentDao.registVideoComment(comment);;
	}

	@Override
	public void updateVideoComment(VideoComment comment) {
		// TODO Auto-generated method stub
		videoCommentDao.updateVideoComment(comment);;
	}

	@Override
	public void deleteVideoComment(int videocomment_num) {
		// TODO Auto-generated method stub
		videoCommentDao.deleteVideoComment(videocomment_num);
	}

}
