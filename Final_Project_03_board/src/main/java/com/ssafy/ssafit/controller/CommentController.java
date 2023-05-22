package com.ssafy.ssafit.controller;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Comment;
import com.ssafy.ssafit.model.service.CommentService;
import com.ssafy.ssafit.util.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@RequestMapping("/ssafit/postComment")
@Api(tags = "댓글 컨트롤러")
public class CommentController {
	
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@GetMapping("/read/{post_num}")
	@ApiOperation(value="댓글 목록 불러오기", notes = "video_id로 해당 영상 댓글 불러오기")
	public ResponseEntity<?> selectComment(@PathVariable int post_num) {
		System.out.println(post_num);
		List<Comment> commentlist = commentService.selectComment(post_num);
	return new ResponseEntity<List<Comment>>(commentlist, HttpStatus.OK);
	}
	
	@PostMapping("/regist")
	@ApiOperation(value="댓글 등록", notes = "댓글 등록하기 (DB추가)")
	public ResponseEntity<?> registComment(@RequestBody HashMap<String, Object> requestJsonHashMap) throws IOException {

		String user_id = jwtUtil.parse((String) requestJsonHashMap.get("token"));
		Comment comment = new Comment();
		comment.setPost_num((int) requestJsonHashMap.get("post_num"));
		comment.setComment_content((String) requestJsonHashMap.get("comment_content"));
		comment.setComment_writer_id(user_id);
		System.out.println((String) requestJsonHashMap.get("token"));
		
		commentService.registComment(comment);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@GetMapping("/delete/{comment_num}/{token}")
	@ApiOperation(value="댓글 삭제", notes = "댓글 삭제하기 (DB삭제)")
	public ResponseEntity<?> deleteComment(@PathVariable int comment_num,@PathVariable String token, @ApiIgnore HttpServletResponse resp) throws IOException {
		
		System.out.println(token);
		System.out.println(token);
		System.out.println(token);
		System.out.println(token);
		System.out.println(comment_num);
		System.out.println(comment_num);
		
		Comment comment = commentService.selectOne(comment_num);
		if(!comment.getComment_writer_id().equals(jwtUtil.parse((token)))){
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		commentService.deleteComment(comment_num);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/update")
    @ApiOperation(value="댓글 수정", notes = "댓글 수정하기 (DB변경)")
    public ResponseEntity<?> updateComment(@RequestBody Comment comment, @ApiIgnore HttpServletResponse resp,String token) throws IOException {
        
        if(jwtUtil.parse(token)!=comment.getComment_writer_id())
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        
        commentService.updateComment(comment);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	
}