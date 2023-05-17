DROP DATABASE IF EXISTS ssafit_final;
CREATE DATABASE IF NOT EXISTS ssafit_final;
USE ssafit_final;

CREATE SCHEMA IF NOT EXISTS `ssafit_final` DEFAULT CHARACTER SET utf8mb4 ;
USE `ssafit_final` ;

-- -----------------------------------------------------
-- Table `ssafit_final`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit_final`.`User` (
  `user_id` VARCHAR(45) NOT NULL,
  `user_pw` VARCHAR(45) NOT NULL,
  `user_name` VARCHAR(20) NOT NULL,
  `user_email` VARCHAR(45) NOT NULL,
  `user_phone` VARCHAR(45) NOT NULL,
  `user_imgFolder` VARCHAR(200) NULL,
  `user_imgFile` VARCHAR(200) NULL,
  `user_nickname` VARCHAR(20) NOT NULL,
  `user_height` INT NULL,
  `user_weight` INT NULL,
  `user_point` INT NOT NULL DEFAULT 0,
  `user_created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_deleted_at` TIMESTAMP NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_nickname_UNIQUE` (`user_nickname` ASC) VISIBLE,
  UNIQUE INDEX `user_phone_UNIQUE` (`user_phone` ASC) VISIBLE,
  UNIQUE INDEX `user_email_UNIQUE` (`user_email` ASC) VISIBLE)
ENGINE = InnoDB;

INSERT INTO `user` (user_id, user_pw, user_name, user_email, user_phone, user_nickname ) 
VALUES
('ssafy', '1234','김싸피', 'ssafy@ssafy.com', '010-1234-1234', '관리자'),
('rudtjq', '1234','심경섭', '11goatmxj@ssafy.com', '010-5678-5678', '조이'),
('alsgml', '1234','정민희', 'mong2072@ssafy.com', '010-2727-2727', '미늬'),
('codud', '1234','이채영', 'coco@ssafy.com', '010-0505-2424', '채채'),
('whdals', '1234','이종민', 'jjong@ssafy.com', '010-8596-8596', '라따뚜이');


-- -----------------------------------------------------
-- Table `ssafit_final`.`Record`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit_final`.`Record` (
  `record_num` INT NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `record_created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `record_modified_at` TIMESTAMP NULL,
  `record_recordDate` DATE NULL,
  `record_recordTime` TIME NULL,
  `record_title` VARCHAR(100) NULL,
  `record_content` VARCHAR(200) NULL,
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  PRIMARY KEY (`record_num`),
  CONSTRAINT `fk_Record_User`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafit_final`.`User` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit_final`.`Board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit_final`.`Board` (
  `board_id` INT NOT NULL AUTO_INCREMENT,
  `board_name` VARCHAR(45) NOT NULL,
  `board_created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `board_point` INT NULL,
  PRIMARY KEY (`board_id`))
ENGINE = InnoDB;

INSERT INTO `Board` (board_name, board_created_at, board_point) 
VALUES
('공지사항', now(), 0),
('자유게시판', now(), "100"),
('운동관련정보', now(), "200"),
('식단관련정보', now(), "200");


-- -----------------------------------------------------
-- Table `ssafit_final`.`Post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit_final`.`Post` (
  `post_num` INT NOT NULL AUTO_INCREMENT,
  `post_writer_id` VARCHAR(45) NOT NULL,
  `post_board_id` INT NOT NULL,
  `post_title` VARCHAR(45) NOT NULL,
  `post_content` VARCHAR(300) NOT NULL,
  `post_img` VARCHAR(200) NULL,
  `post_created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `post_modified_at` TIMESTAMP NULL,
  `post_view_cnt` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`post_num`),
  INDEX `board_id_idx` (`post_board_id` ASC) VISIBLE,
  CONSTRAINT `fk_Post_Board`
    FOREIGN KEY (`post_board_id`)
    REFERENCES `ssafit_final`.`Board` (`board_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `Post` (post_writer_id, post_board_id, post_title, post_content) 
VALUES
('ssafy', '1', '공지사항글1', '공지사항은 관리자만 작성가능하게 해주세요'),
('ssafy', '1', '공지사항글2', '공지사항은 관리자만 작성가능하게 해주세요'),
('ssafy', '1', '공지사항글3', '공지사항은 관리자만 작성가능하게 해주세요'),
('alsgml', '2', '자유게시글1', '자유게시판에 민희가 쓴 글'),
('alsgml', '2', '자유게시글2', '자유게시판에 민희가 쓴 글'),
('rudtjq', '2', '자유게시글3', '자유게시판에 경섭이가 쓴 글'),
('rudtjq', '2', '자유게시글4', '자유게시판에 경섭이가 쓴 글'),
('alsgml', '3', '운동관련글1', '운동관련글에 민희가 쓴 글'),
('alsgml', '3', '운동관련글2', '운동관련글에 민희가 쓴 글'),
('rudtjq', '3', '운동관련글3', '운동관련글에 경섭이가 쓴 글'),
('rudtjq', '3', '운동관련글4', '운동관련글에 경섭이가 쓴 글'),
('alsgml', '4', '식단관련글1', '식단관련글에 민희가 쓴 글'),
('rudtjq', '4', '식단관련글2', '식단관련글에 경섭이가 쓴 글'),
('codud', '4', '식단관련글3', '식단관련글에 채영이가 쓴 글'),
('whdals', '4', '식단관련글4', '식단관련글에 종민이가 쓴 글');


-- -----------------------------------------------------
-- Table `ssafit_final`.`Comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit_final`.`Comment` (
  `comment_num` INT NOT NULL AUTO_INCREMENT,
  `post_num` INT NOT NULL,
  `comment_writer_id` VARCHAR(45) NOT NULL,
  `comment_content` VARCHAR(200) NOT NULL,
  `comment_created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `comment_modified_at` TIMESTAMP NULL,
  PRIMARY KEY (`comment_num`),
  INDEX `post_num_idx` (`post_num` ASC) ,
  INDEX `writer_idx` (`comment_writer_id` ASC) ,
  CONSTRAINT `fk_Comment_Post`
    FOREIGN KEY (`post_num`)
    REFERENCES `ssafit_final`.`Post` (`post_num`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

INSERT INTO `Comment` (post_num, comment_writer_id, comment_content) 
VALUES
('1', 'alsgml', '확인용댓글입니다.'),
('1', 'rudtjq', '확인용댓글입니다'),
('1', 'codud', '확인용댓글입니다'),
('1', 'whdals', '확인용댓글입니다'),
('4', 'alsgml', '확인용댓글입니다.'),
('4', 'rudtjq', '확인용댓글입니다'),
('4', 'codud', '확인용댓글입니다'),
('8', 'whdals', '확인용댓글입니다'),
('8', 'alsgml', '확인용댓글입니다.'),
('8', 'rudtjq', '확인용댓글입니다'),
('8', 'codud', '확인용댓글입니다'),
('12', 'whdals', '확인용댓글입니다'),
('12', 'alsgml', '확인용댓글입니다.'),
('12', 'rudtjq', '확인용댓글입니다'),
('12', 'codud', '확인용댓글입니다');


-- -----------------------------------------------------
-- Table `ssafit_final`.`PostLike`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit_final`.`PostLike` (
  `postLike_num` VARCHAR(45) NOT NULL,
  `postLike_user_id` VARCHAR(15) NOT NULL,
  `postLike_post_num` INT NOT NULL,
  PRIMARY KEY (`postLike_num`),
  INDEX `like_post_idx` (`postLike_post_num` ASC),
  INDEX `like_id_idx` (`postLike_user_id` ASC),
  CONSTRAINT `fk_PostLike_Post`
    FOREIGN KEY (`postLike_post_num`)
    REFERENCES `ssafit_final`.`Post` (`post_num`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ssafit_final`.`video`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `video` (
    `video_img` varchar(30) not null ,
    `video_id` varchar(30) not null primary key,
    `video_title` varchar(70) not null,
    `video_part` varchar(10) not null,
    `video_channelName` varchar(30) not null,
    `video_url` varchar(100) not null,   
    `video_viewCnt` int DEFAULT 0,
    `video_zzimCnt` int DEFAULT 0
)engine = InnoDB;

INSERT INTO `video` (`video_img`, `video_id`, `video_title`, `video_part`, `video_channelName`, `video_url`) 
VALUE
('img/칼소폭.jpg', 'gMaB-fG4u4g', '전신 다이어트 최고의 운동 [칼소폭 찐 핵핵매운맛]', '전신', 'ThankyouBUBU', 'https://www.youtube.com/embed/gMaB-fG4u4g'),
('img/하루15분전신.jpg', 'swRNeYw1JkY', '하루 15분! 전신 칼로리 불태우는 다이어트 운동', '전신', 'ThankyouBUBU', 'https://www.youtube.com/embed/swRNeYw1JkY'),
('img/상체다이어트팔뚝살.jpg','54tTYO-vU2E','상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]','상체','ThankyouBUBU','https://www.youtube.com/embed/54tTYO-vU2E'),
('img/상체비만.jpg','QqqZH3j_vH0','상체비만 다이어트 최고의 운동 [상체 핵매운맛]','상체','ThankyouBUBU','https://www.youtube.com/embed/QqqZH3j_vH0'),
('img/하체운동이중요한이유.jpg','tzN6ypk6Sps','하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]','하체','김강민','https://www.youtube.com/embed/tzN6ypk6Sps'),
('img/짐종국 썸네일.jpg','u5OgcZdNbMo','저는 하체 식주의자 입니다','하체','GYM종국','https://www.youtube.com/embed/u5OgcZdNbMo'),
('img/누워서하는5분.jpg','7TLk7pscICk','(Sub)누워서하는 5분 복부운동!! 효과보장! (매일 2주만 해보세요!)','복부','SomiFit','https://www.youtube.com/embed/7TLk7pscICk'),
('img/앉아서하는스트레칭.webp','2DRvc74GgYM','앉아서 하는 스트레칭 루틴! | 의자 스트레칭','전신','관절사용설명서','https://www.youtube.com/watch?v=2DRvc74GgYM'),
('img/코어운동.webp','_RKJL6vwx_U','코어운동 초보 루틴 BESTㅣ하루 10분으로 초보자 코어근육강화!','전신','관절사용설명서','https://www.youtube.com/watch?v=_RKJL6vwx_U');


-- -----------------------------------------------------
-- Table `ssafit_final`.`videoComment`
-- -----------------------------------------------------

create table if not exists videoComment (
    `videocomment_num` int auto_increment primary Key,
    `videocomment_video_id` varchar(30) not null,
    `videocomment_writer_id` varchar(40) not null,
    `videocomment_created_at` TIMESTAMP DEFAULT now(),
    `videocomment_content` varchar(200) not null,
    `videocomment_modified_at` TIMESTAMP DEFAULT now(),
    foreign key (`videocomment_video_id`) references video(`video_id`) on delete cascade,
    foreign key (`videocomment_writer_id`) references user(`user_id`)
)engine = InnoDB;

INSERT INTO `videoComment` (`videocomment_video_id`, `videocomment_writer_id`, `videocomment_content`) 
VALUE
('gMaB-fG4u4g', 'ssafy', '김싸피의 리뷰 운동했어요1'),
('gMaB-fG4u4g', 'alsgml', '정민희의 리뷰 운동했어요2'),
('gMaB-fG4u4g','rudtjq','심경섭의 리뷰 운동했어요3'),
('swRNeYw1JkY', 'ssafy', '김싸피의 리뷰 운동했어요1'),
('swRNeYw1JkY', 'alsgml', '정민희의 리뷰 운동했어요2'),
('swRNeYw1JkY','rudtjq','심경섭의 리뷰 운동했어요3'),
('54tTYO-vU2E', 'ssafy', '김싸피의 리뷰 운동했어요1'),
('54tTYO-vU2E', 'alsgml', '정민희의 리뷰 운동했어요2'),
('54tTYO-vU2E', 'rudtjq','심경섭의 리뷰 운동했어요3'),
('QqqZH3j_vH0', 'ssafy', '김싸피의 리뷰 운동했어요1'),
('QqqZH3j_vH0', 'alsgml', '정민희의 리뷰 운동했어요2'),
('QqqZH3j_vH0','rudtjq','심경섭의 리뷰 운동했어요3'),
('tzN6ypk6Sps', 'ssafy', '김싸피의 리뷰 운동했어요1'),
('tzN6ypk6Sps', 'alsgml', '정민희의 리뷰 운동했어요2'),
('tzN6ypk6Sps', 'rudtjq','심경섭의 리뷰 운동했어요3'),
('u5OgcZdNbMo', 'ssafy', '김싸피의 리뷰 운동했어요1'),
('u5OgcZdNbMo', 'alsgml', '정민희의 리뷰 운동했어요2'),
('u5OgcZdNbMo', 'rudtjq', '심경섭의 리뷰 운동했어요3'),
('7TLk7pscICk', 'ssafy', '김싸피의 리뷰 운동했어요1'),
('7TLk7pscICk', 'alsgml', '정민희의 리뷰 운동했어요2'),
('7TLk7pscICk', 'rudtjq', '심경섭의 리뷰 운동했어요3'),
('2DRvc74GgYM', 'ssafy', '김싸피의 리뷰 운동했어요1'),
('2DRvc74GgYM', 'alsgml', '정민희의 리뷰 운동했어요2'),
('2DRvc74GgYM', 'rudtjq', '심경섭의 리뷰 운동했어요3'),
('_RKJL6vwx_U', 'ssafy', '김싸피의 리뷰 운동했어요1'),
('_RKJL6vwx_U', 'alsgml', '정민희의 리뷰 운동했어요2'),
('_RKJL6vwx_U', 'rudtjq', '심경섭의 리뷰 운동했어요3');

USE ssafit_final;