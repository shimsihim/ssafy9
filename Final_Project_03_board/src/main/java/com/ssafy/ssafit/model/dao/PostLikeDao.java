package com.ssafy.ssafit.model.dao;

import com.ssafy.ssafit.model.dto.PostLike;

public interface PostLikeDao {
	int checkPostLike(PostLike postlike);

	void insertPostLike(PostLike postlike);

	void deletePostLike(PostLike postlike);
	
	int countPostLike(int postLike_post_num);
}
