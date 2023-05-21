package com.ssafy.ssafit.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.service.UserService;
import com.ssafy.ssafit.util.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/ssafit/user")
@Api(tags = "유저 컨트롤러")
public class UserController {
	//private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Autowired
	private JwtUtil jwtUtil;
	
	
	

//	
	@PostMapping("/login")
	@ApiOperation(value="로그인", notes = "이름 반환으로 로그인 유저 확인")
	public ResponseEntity<?> login(String user_id, String user_pw) { //@ApiIgnore HttpSession session) {
		User tmpUser = userService.login(user_id,user_pw);
		Map<String,Object> result = new HashMap<String, Object>();
		HttpStatus status = null;
		
		try {
			if(tmpUser!=null) {
				result.put("access-token",jwtUtil.createToken("id",tmpUser.getUser_id()));
				result.put("message",tmpUser.getUser_nickname());
				status = HttpStatus.ACCEPTED;
			}else {
				result.put("message","fail");
				status = HttpStatus.NO_CONTENT;
			}
		}
		catch(UnsupportedEncodingException e) {
			result.put("message","fail");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			
		}
		return new ResponseEntity<Map<String,Object>>(result , status);
		
	}
	
	@GetMapping("/logout")
	@ApiOperation(value="로그아웃", notes = "이름 반환으로 로그아웃 확인")
	public ResponseEntity<?> logout(@ApiIgnore HttpSession session) {
		if(session.getAttribute("loginUser") == null) {
			return new ResponseEntity<String>("loginUser is null.",HttpStatus.BAD_REQUEST);
		}
		String tmp = ((User)session.getAttribute("loginUser")).getUser_id();
		session.invalidate();
		return new ResponseEntity<String>("logout : "+tmp, HttpStatus.OK);
	}
	
	
	@GetMapping("/userDetail/{user_id}")
	@ApiOperation(value="유저 상세보기", notes = "아이디로 유저 1명의 정보 상세조회,마이페이지에 쓰일 것.")
	public ResponseEntity<?> userDetail(@PathVariable String user_id, @ApiIgnore HttpSession session) {
		
		User user = userService.userDetail(user_id);
		
 //=> 로그인되어있는 유저 본인만 조회가 가능하도록 하기
		if(((User)session.getAttribute("loginUser")).getUser_id().equals(user.getUser_id())) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		//return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	
	
	@GetMapping("/idCheck/{user_id}")
	@ApiOperation(value="유저 상세보기", notes = "아이디로 유저 1명의 정보 상세조회,마이페이지에 쓰일 것.")
	public ResponseEntity<?> idCheck(@PathVariable String user_id, @ApiIgnore HttpSession session) {
		
		User user = userService.userDetail(user_id);
		
		//=> 로그인되어있는 유저 본인만 조회가 가능하도록 하기
		if(user != null){
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		//return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	
	
	
	@PostMapping("/signUp")
	@ApiOperation(value="회원가입", notes = "회원가입 (DB에 추가)")
	public ResponseEntity<?> signUp(@RequestBody User user){
		userService.signUp(user);
		return new ResponseEntity<Void>(HttpStatus.CREATED);


//		try {
//			userService.signUp(user, file);
//			return new ResponseEntity<Void>(HttpStatus.CREATED);
//		} catch (Throwable e) {
//			e.printStackTrace();
//			return new ResponseEntity<Void>(HttpStatus.I_AM_A_TEAPOT);
//		}
	}
	
	@GetMapping("/selectAll")
	@ApiOperation(value="전체 조회", notes = "전체 유저리스트 반환")
	public ResponseEntity<?> selectAll() {
		
		List<User> userlist = userService.selectAll();
		return new ResponseEntity<List<User>>(userlist, HttpStatus.OK);
	}
	
	@PostMapping("/updateUserInfo")
	@ApiOperation(value="회원정보수정", notes = "회원정보수정 (DB 업데이트)")
	public ResponseEntity<Void> updateUserInfo(@RequestBody User user){
		userService.updateUserInfo(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	//유저요청 후 일정시간 후에 삭제 되면 좋을 듯...??
	// 탈퇴 시 바로 삭제가 아닌 탈퇴 일자를 적어두기.
	@DeleteMapping("/withdraw/{user_id}")
	@ApiOperation(value="회원탈퇴", notes = "회원정보삭제 (DB 삭제)")
	public ResponseEntity<Void> withdraw(@PathVariable String user_id){
		userService.withdraw(user_id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	

	
	

}
