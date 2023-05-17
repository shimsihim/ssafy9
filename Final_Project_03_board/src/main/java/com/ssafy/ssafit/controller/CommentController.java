package com.ssafy.ssafit.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Comment;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

import com.ssafy.ssafit.model.service.CommentService;


@RestController
@RequestMapping("/ssafit/comment")
@Api(tags = "댓글 컨트롤러")
public class CommentController {
	
	
	@Autowired
	CommentService commentService;
	
	
	@GetMapping("/regist")
	@ApiOperation(value="댓글 등록", notes = "댓글 등록하기 (DB추가)")
	public ResponseEntity<?> registComment(@RequestBody Comment comment) throws IOException {
		commentService.registComment(comment);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@GetMapping("/delete")
	@ApiOperation(value="댓글 삭제", notes = "댓글 삭제하기 (DB삭제)")
	public ResponseEntity<?> deleteComment(int id, int article, @ApiIgnore HttpServletResponse resp) throws IOException {
		commentService.deleteComment(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/update")
	@ApiOperation(value="댓글 수정", notes = "댓글 수정하기 (DB변경)")
	public ResponseEntity<?> updateComment(@RequestBody Comment comment, @ApiIgnore HttpServletResponse resp) throws IOException {
		commentService.updateComment(comment);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
}
