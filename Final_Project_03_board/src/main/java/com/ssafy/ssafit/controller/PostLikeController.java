package com.ssafy.ssafit.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.PostLike;
import com.ssafy.ssafit.model.service.PostLikeService;
import com.ssafy.ssafit.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ssafit/postLike")
@Api(tags = "게시글 컨트롤러")
public class PostLikeController {

//    @Autowired
//    PostService postService;
//    
//    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    PostLikeService likeService;

	@GetMapping("/check/{post_num}/{token}")
	@ApiOperation(value = "게시판별 글 목록", notes = "board_id로 게시판 별 글 전체 불러오기")
	public ResponseEntity<?> readPostAll(@PathVariable int post_num, @PathVariable String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException{
		PostLike pl = new PostLike();
		pl.setPostLike_post_num(post_num);
		pl.setPostLike_user_id(jwtUtil.parse(token));
		
		if(likeService.checkPostLike(pl)==0) {
			return  new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}

	@GetMapping("/insert/{post_num}/{token}")  // 해당 게시물에 좋아여 추가
	@ApiOperation(value="글 등록", notes = "게시글 등록하기 (DB확인)")
	public ResponseEntity<?> insertLike(@PathVariable int post_num, @PathVariable String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		
		PostLike pl = new PostLike();
		pl.setPostLike_post_num(post_num);
		pl.setPostLike_user_id(jwtUtil.parse(token));
		
		likeService.insertPostLike(pl);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	
	@GetMapping("/delete/{post_num}/{token}")  // 해당 게시물에 좋아여 추가
	@ApiOperation(value="글 등록", notes = "게시글 등록하기 (DB확인)")
	public ResponseEntity<?> deleteLike(@PathVariable int post_num, @PathVariable String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		
		PostLike pl = new PostLike();
		pl.setPostLike_post_num(post_num);
		pl.setPostLike_user_id(jwtUtil.parse(token));
		
		likeService.deletePostLike(pl);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
