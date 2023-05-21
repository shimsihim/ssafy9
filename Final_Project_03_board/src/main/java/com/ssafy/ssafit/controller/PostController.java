package com.ssafy.ssafit.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Post;
import com.ssafy.ssafit.model.service.CommentService;
import com.ssafy.ssafit.model.service.PostService;
import com.ssafy.ssafit.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/ssafit/post")
@Api(tags = "게시글 컨트롤러")
public class PostController {
    
    @Autowired
    PostService postService;
    @Autowired
    CommentService commentService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @GetMapping("/board/{board_id}")
    @ApiOperation(value="게시판별 글 목록", notes = "board_id로 게시판 별 글 전체 불러오기")
    public ResponseEntity<?> readPostAll(@PathVariable int board_id) {
        System.out.println(board_id);
        List<Post> postlist = postService.readPostAll(board_id);
    return new ResponseEntity<List<Post>>(postlist, HttpStatus.OK);
    }
	
	@PostMapping("/regist")
	@ApiOperation(value="글 등록", notes = "게시글 등록하기 (DB확인)")
	public ResponseEntity<?> registPost(@RequestBody HashMap<String, Object> req) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		
		String user_id = jwtUtil.parse((String) req.get("token"));
		Post post = new Post();
		post.setPost_board_id((int) req.get("post_board_id"));
		post.setPost_writer_id(user_id);
		post.setPost_title((String)req.get("post_title"));
		post.setPost_content((String)req.get("post_content"));
		
		postService.registPost(post);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	//여러개 응답 보내는거 확인하기 & 댓글 서비스 만들고 난후 댓글도 불러오게 하기
	@GetMapping("/read/{post_num}") 
	@ApiOperation(value="특정 게시글 상세보기", notes = "post_num 입력시 특정 게시글과 댓글들을 조회 ")
	public ResponseEntity<?> readPost(@PathVariable int post_num) {
		List<Object> detailPage = new ArrayList<>();
		detailPage.add(postService.readPost(post_num)); //Post
		return new ResponseEntity<List<Object>>(detailPage,HttpStatus.OK);
	}
	
	@PostMapping("/update")
	@ApiOperation(value="게시글 수정", notes = "게시글 수정하기 (DB확인)")
	public ResponseEntity<?> updatePost(@RequestBody HashMap<String, Object> req) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		
		int post_num = (int) req.get("post_num");  // 일단 게시물 번호를 가져와
		Post post = postService.selectPost(post_num); // 해당 게시물 가져오고
		if(post==null || !jwtUtil.parse((String) req.get("token")).equals(post.getPost_writer_id())) { //작성자 아이디와의 일치여부 확인 후
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST); // 일치 x거나 없는 게시물이면 반환
		}
		
		
		post.setPost_board_id((int)req.get("post_board_id")); // 수정된 값들 세팅
		post.setPost_title((String)req.get("post_title"));
		post.setPost_content((String)req.get("post_content"));
		
		
		postService.updatePost(post);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{post_num}/{token}")
	@ApiOperation(value="게시글 삭제", notes = "게시글 삭제하기 (DB확인)")
	public ResponseEntity<?> deletePost(@PathVariable int post_num , @PathVariable String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		
		Post post = postService.selectPost(post_num); // 해당 게시물 가져오고
		if(post==null || !jwtUtil.parse(token).equals(post.getPost_writer_id())) { //작성자 아이디와의 일치여부 확인 후
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST); // 일치 x거나 없는 게시물이면 반환
		}
		
		postService.deletePost(post_num);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	

}
