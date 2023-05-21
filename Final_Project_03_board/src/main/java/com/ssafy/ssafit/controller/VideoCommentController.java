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

import com.ssafy.ssafit.model.dto.VideoComment;
import com.ssafy.ssafit.model.service.VideoCommentService;
import com.ssafy.ssafit.util.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@RequestMapping("/ssafit/videoComment")
@Api(tags = "영상 댓글 컨트롤러")
public class VideoCommentController {
	
	@Autowired
    private JwtUtil jwtUtil;
	
	@Autowired
	VideoCommentService videoCommentService;
	
	@GetMapping("/read/{video_id}")
	@ApiOperation(value="댓글 목록 불러오기", notes = "video_id로 해당 영상 댓글 불러오기")
	public ResponseEntity<?> selectVideoComment(@PathVariable String video_id) {
		List<VideoComment> commentlist = videoCommentService.selectVideoComment(video_id);
	return new ResponseEntity<List<VideoComment>>(commentlist, HttpStatus.OK);
	}
	
	@PostMapping("/regist")
    @ApiOperation(value="영상 댓글 등록", notes = "댓글 등록하기 (DB추가)")
    public ResponseEntity<?> registVideoComment(@RequestBody HashMap<String, Object> requestJsonHashMap) throws IOException {
        String user_id = jwtUtil.parse((String) requestJsonHashMap.get("token"));
        VideoComment comment = new VideoComment();
        comment.setVideocomment_writer_id(user_id);
        comment.setVideocomment_content((String) requestJsonHashMap.get("videocomment_content"));
        comment.setVideocomment_video_id((String) requestJsonHashMap.get("videocomment_video_id"));
        
        
        videoCommentService.registVideoComment(comment);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	
	@GetMapping("/delete/{videocomment_num}/{token}")
    @ApiOperation(value="댓글 삭제", notes = "댓글 삭제하기 (DB삭제)")
    public ResponseEntity<?> deleteComment(@PathVariable int videocomment_num, @ApiIgnore HttpServletResponse resp,@PathVariable String token) throws IOException {
        
		System.out.println(videocomment_num);
		System.out.println(videocomment_num);
		System.out.println(videocomment_num);
		System.out.println(token);
		System.out.println(token);
        if(!jwtUtil.parse(token).equals(videoCommentService.getVideoCommentWriterId(videocomment_num))) {
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);}
       
        videoCommentService.deleteVideoComment(videocomment_num);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    @GetMapping("/update")
    @ApiOperation(value="댓글 수정", notes = "댓글 수정하기 (DB변경)")
    public ResponseEntity<?> updateComment(@RequestBody VideoComment videoComment, @ApiIgnore HttpServletResponse resp, String token) throws IOException {
        
        if(jwtUtil.parse(token)!=videoComment.getVideocomment_writer_id())
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        
        videoCommentService.updateVideoComment(videoComment);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	
}
