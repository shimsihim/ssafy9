package com.ssafy.ssafit.model.dto;

import java.sql.Timestamp;

public class VideoComment {
	private String videocomment_video_id;
	private int videocomment_num;
	private String videocomment_writer_id;
	private String videocomment_content;
	private String videocomment_user_nickname;
	private Timestamp videocomment_created_at;
	private Timestamp videocomment_modified_at;

	public VideoComment() {
		super();
	}

	public VideoComment(String videocomment_video_id, int videocomment_num, String videocomment_writer_id,
			String videocomment_content, String videocomment_user_nickname, Timestamp videocomment_created_at,
			Timestamp videocomment_modified_at) {
		super();
		this.videocomment_video_id = videocomment_video_id;
		this.videocomment_num = videocomment_num;
		this.videocomment_writer_id = videocomment_writer_id;
		this.videocomment_content = videocomment_content;
		this.videocomment_user_nickname = videocomment_user_nickname;
		this.videocomment_created_at = videocomment_created_at;
		this.videocomment_modified_at = videocomment_modified_at;
	}

	public String getVideocomment_video_id() {
		return videocomment_video_id;
	}

	public void setVideocomment_video_id(String videocomment_video_id) {
		this.videocomment_video_id = videocomment_video_id;
	}

	public int getVideocomment_num() {
		return videocomment_num;
	}

	public void setVideocomment_num(int videocomment_num) {
		this.videocomment_num = videocomment_num;
	}

	public String getVideocomment_writer_id() {
		return videocomment_writer_id;
	}

	public void setVideocomment_writer_id(String videocomment_writer_id) {
		this.videocomment_writer_id = videocomment_writer_id;
	}

	public String getVideocomment_content() {
		return videocomment_content;
	}

	public void setVideocomment_content(String videocomment_content) {
		this.videocomment_content = videocomment_content;
	}

	public String getVideocomment_user_nickname() {
		return videocomment_user_nickname;
	}

	public void setVideocomment_user_nickname(String videocomment_user_nickname) {
		this.videocomment_user_nickname = videocomment_user_nickname;
	}

	public Timestamp getVideocomment_created_at() {
		return videocomment_created_at;
	}

	public void setVideocomment_created_at(Timestamp videocomment_created_at) {
		this.videocomment_created_at = videocomment_created_at;
	}

	public Timestamp getVideocomment_modified_at() {
		return videocomment_modified_at;
	}

	public void setVideocomment_modified_at(Timestamp videocomment_modified_at) {
		this.videocomment_modified_at = videocomment_modified_at;
	}

	@Override
	public String toString() {
		return "VideoComment [videocomment_video_id=" + videocomment_video_id + ", videocomment_num=" + videocomment_num
				+ ", videocomment_writer_id=" + videocomment_writer_id + ", videocomment_content="
				+ videocomment_content + ", videocomment_user_nickname=" + videocomment_user_nickname
				+ ", videocomment_created_at=" + videocomment_created_at + ", videocomment_modified_at="
				+ videocomment_modified_at + "]";
	}

}
