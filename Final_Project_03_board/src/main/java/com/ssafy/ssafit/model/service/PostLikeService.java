package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dto.PostLike;

public interface PostLikeService {

	int checkPostLike(PostLike postlike);

	void insertPostLike(PostLike postlike);

	void deletePostLike(PostLike postlike);

}
