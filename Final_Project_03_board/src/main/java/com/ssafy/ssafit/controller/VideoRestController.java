package com.ssafy.ssafit.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Video;
import com.ssafy.ssafit.model.dto.VideoComment;
import com.ssafy.ssafit.model.service.VideoCommentService;
import com.ssafy.ssafit.model.service.VideoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ssafit/video")
@Api(tags = "비디오 컨트롤러")
public class VideoRestController {
	
	@Autowired
	private VideoService videoService;
	@Autowired
	private VideoCommentService commentService;

	@ApiOperation(value="전체 영상 조회", notes = "전체 영상 조회")
	@GetMapping("/list")
	public ResponseEntity<?> list(){
		List<Video> videolist = videoService.selectAll(); 
		
		if(videolist == null || videolist.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Video>>(videolist, HttpStatus.OK);
	}
	
	@ApiOperation(value="부위별 영상 조회", notes = "part를 입력하면 해당하는 영상을 불러옴") //  해당 영상에 ㄷ ㅐ해서 코멘트 불러오기 , db바꿔주기
	@GetMapping("/partly/{video_part}")
	public ResponseEntity<?> partlyList(@PathVariable String video_part){
		List<Video> partlyList = videoService.selectPartly(video_part);
		System.out.println(partlyList);
		
		if(partlyList == null || partlyList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Video>>(partlyList, HttpStatus.OK);
	}
	
	@ApiOperation(value="특정 영상 조회", notes = "videoID를 입력하면 해당하는 영상을 불러옴")
	@GetMapping("/detail/{video_id}")
	public ResponseEntity<?> detail(@PathVariable String video_id){
		try {
			List<Object> detailPage = new ArrayList<>();
			Video vid = videoService.selectOne(video_id);
			detailPage.add(videoService.selectOne(video_id)); //Post
			
			List<VideoComment> com = commentService.selectVideoComment(video_id);
			
			detailPage.addAll(commentService.selectVideoComment(video_id)); //Post
			
			return new ResponseEntity<List<Object>>(detailPage, HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
}