package com.ssafy.ssafit.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.PostLikeDao;
import com.ssafy.ssafit.model.dto.PostLike;


@Service
public class PostLikeServiceImpl implements PostLikeService{

	
	
	PostLikeDao dao;

	@Autowired
	public void setDao(PostLikeDao dao) {
		this.dao = dao;
	}

	
	@Override
	public int checkPostLike(PostLike postlike) {
		// TODO Auto-generated method stub
		return dao.checkPostLike(postlike);
	}

	@Override
	public void insertPostLike(PostLike postlike) {
		// TODO Auto-generated method stub
		dao.insertPostLike(postlike);
	}

	@Override
	public void deletePostLike(PostLike postlike) {
		// TODO Auto-generated method stub
		dao.deletePostLike(postlike);
	}

}
