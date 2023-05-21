DROP DATABASE IF EXISTS `ssafit_final` ;
-- -----------------------------------------------------
-- Schema ssafit_final
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS `ssafit_final` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ssafit_final` ;

-- -----------------------------------------------------
-- Table `ssafit_final`.`challenge`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit_final`.`challenge` (
  `challenge_id` INT NOT NULL AUTO_INCREMENT,
  `challenge_sort` VARCHAR(45) NOT NULL,
  `challenge_location` VARCHAR(45) NOT NULL,
  `challenge_title` VARCHAR(45) NOT NULL,
  `challenge_content` VARCHAR(45) NOT NULL,
  `challenge_cnt` INT NOT NULL,
  `challenge_endDate` DATE NOT NULL,
  `challenge_makeUser` VARCHAR(45) NULL,
  PRIMARY KEY (`challenge_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafit_final`.`participant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit_final`.`participant` (
  `participant_id` INT NOT NULL,
  `participant_challenge_id` INT NOT NULL,
  `participant_user_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`participant_id`),
  INDEX `challenge_idx` (`participant_challenge_id` ASC) VISIBLE,
  CONSTRAINT `challenge`
    FOREIGN KEY (`participant_challenge_id`)
    REFERENCES `ssafit_final`.`challenge` (`challenge_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `ssafit_final` ;

-- -----------------------------------------------------
-- Table `ssafit_final`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit_final`.`board` (
  `board_id` INT NOT NULL AUTO_INCREMENT,
  `board_name` VARCHAR(45) NOT NULL,
  `board_created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `board_point` INT NULL DEFAULT NULL,
  PRIMARY KEY (`board_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafit_final`.`post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit_final`.`post` (
  `post_num` INT NOT NULL AUTO_INCREMENT,
  `post_writer_id` VARCHAR(45) NOT NULL,
  `post_board_id` INT NOT NULL,
  `post_title` VARCHAR(45) NOT NULL,
  `post_content` VARCHAR(300) NOT NULL,
  `post_img` VARCHAR(200) NULL DEFAULT NULL,
  `post_created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `post_modified_at` TIMESTAMP NULL DEFAULT NULL,
  `post_view_cnt` INT NOT NULL DEFAULT '0',
  PRIMARY KEY (`post_num`),
  CONSTRAINT `fk_Post_Board`
    FOREIGN KEY (`post_board_id`)
    REFERENCES `ssafit_final`.`board` (`board_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafit_final`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit_final`.`comment` (
  `comment_num` INT NOT NULL AUTO_INCREMENT,
  `post_num` INT NOT NULL,
  `comment_writer_id` VARCHAR(45) NOT NULL,
  `comment_content` VARCHAR(200) NOT NULL,
  `comment_created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `comment_modified_at` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`comment_num`),
  INDEX `post_num_idx` (`post_num` ASC) VISIBLE,
  INDEX `writer_idx` (`comment_writer_id` ASC) VISIBLE,
  CONSTRAINT `fk_Comment_Post`
    FOREIGN KEY (`post_num`)
    REFERENCES `ssafit_final`.`post` (`post_num`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafit_final`.`postlike`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit_final`.`postlike` (
  `postLike_num` VARCHAR(45) NOT NULL,
  `postLike_user_id` VARCHAR(15) NOT NULL,
  `postLike_post_num` INT NOT NULL,
  PRIMARY KEY (`postLike_num`),
  INDEX `like_post_idx` (`postLike_post_num` ASC) VISIBLE,
  INDEX `like_id_idx` (`postLike_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_PostLike_Post`
    FOREIGN KEY (`postLike_post_num`)
    REFERENCES `ssafit_final`.`post` (`post_num`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafit_final`.`record_ex`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit_final`.`record_ex` (
  `record_ex_num` INT NOT NULL AUTO_INCREMENT,
  `record_ex_date` DATE NULL DEFAULT (current_date),
  `record_ex_time` INT NOT NULL,
  `record_ex_memo` VARCHAR(200) NULL DEFAULT NULL,
  `record_part` VARCHAR(45) NOT NULL,
  `record_ex_user_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`record_ex_num`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafit_final`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit_final`.`user` (
  `user_id` VARCHAR(45) NOT NULL,
  `user_pw` VARCHAR(45) NOT NULL,
  `user_name` VARCHAR(20) NOT NULL,
  `user_email` VARCHAR(45) NOT NULL,
  `user_imgFolder` VARCHAR(200) NULL DEFAULT NULL,
  `user_imgFile` VARCHAR(200) NULL DEFAULT NULL,
  `user_nickname` VARCHAR(20) NOT NULL,
  `user_height` INT NULL DEFAULT NULL,
  `user_weight` INT NULL DEFAULT NULL,
  `user_point` INT NOT NULL DEFAULT '0',
  `user_created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_deleted_at` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_nickname_UNIQUE` (`user_nickname` ASC) VISIBLE,
  UNIQUE INDEX `user_email_UNIQUE` (`user_email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafit_final`.`videocomment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit_final`.`videocomment` (
  `videocomment_num` INT NOT NULL AUTO_INCREMENT,
  `videocomment_video_id` VARCHAR(30) NOT NULL,
  `videocomment_writer_id` VARCHAR(45) NOT NULL,
  `videocomment_created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `videocomment_content` VARCHAR(200) NOT NULL,
  `videocomment_modified_at` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`videocomment_num`),
  INDEX `videocomment_video_id` (`videocomment_video_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ssafit_final`.`record_food`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafit_final`.`record_food` (
  `record_food_num` INT NOT NULL AUTO_INCREMENT,
  `record_food_date` DATE NOT NULL DEFAULT (current_date),
  `record_food_carbo` INT NOT NULL,
  `record_food_protein` INT NOT NULL,
  `record_food_fat` INT NOT NULL,
  `record_food_user_id` VARCHAR(45) NOT NULL,
  `record_food_cal` INT NOT NULL,
  PRIMARY KEY (`record_food_num`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;




-- -----------------------------------------------------
-- SET TABLES
-- -----------------------------------------------------
INSERT INTO `user` (user_id, user_pw, user_name, user_email, user_nickname ) 
VALUES
('ssafy', '1234','김싸피', 'ssafy@ssafy.com', '관리자'),
('rudtjq', '1234','심경섭', '11goatmxj@ssafy.com', '조이'),
('alsgml', '1234','정민희', 'mong2072@ssafy.com', '미늬'),
('codud', '1234','이채영', 'coco@ssafy.com', '채채'),
('whdals', '1234','이종민', 'jjong@ssafy.com', '라따뚜이');

INSERT INTO `Board` (board_name, board_created_at, board_point) 
VALUES
('공지사항', now(), 0),
('자유게시판', now(), "100"),
('운동관련정보', now(), "200"),
('식단관련정보', now(), "200");


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

select * from videoComment;