package com.ssafy.ssafit.model.dto;

public class Video {
	private String video_title;
	private String video_part;
	private String video_id;
	private String video_url;
	private String video_channelName;
	private String video_img;
	private int video_viewCnt;
	private int video_zzimCnt;
	public Video() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Video(String video_title, String video_part, String video_id, String video_url, String video_channelName,
			String video_img, int video_viewCnt, int video_zzimCnt) {
		super();
		this.video_title = video_title;
		this.video_part = video_part;
		this.video_id = video_id;
		this.video_url = video_url;
		this.video_channelName = video_channelName;
		this.video_img = video_img;
		this.video_viewCnt = video_viewCnt;
		this.video_zzimCnt = video_zzimCnt;
	}
	public String getVideo_title() {
		return video_title;
	}
	public void setVideo_title(String video_title) {
		this.video_title = video_title;
	}
	public String getVideo_part() {
		return video_part;
	}
	public void setVideo_part(String video_part) {
		this.video_part = video_part;
	}
	public String getVideo_id() {
		return video_id;
	}
	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}
	public String getVideo_url() {
		return video_url;
	}
	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}
	public String getVideo_channelName() {
		return video_channelName;
	}
	public void setVideo_channelName(String video_channelName) {
		this.video_channelName = video_channelName;
	}
	public String getVideo_img() {
		return video_img;
	}
	public void setVideo_img(String video_img) {
		this.video_img = video_img;
	}
	public int getVideo_viewCnt() {
		return video_viewCnt;
	}
	public void setVideo_viewCnt(int video_viewCnt) {
		this.video_viewCnt = video_viewCnt;
	}
	public int getVideo_zzimCnt() {
		return video_zzimCnt;
	}
	public void setVideo_zzimCnt(int video_zzimCnt) {
		this.video_zzimCnt = video_zzimCnt;
	}
	@Override
	public String toString() {
		return "Video [video_title=" + video_title + ", video_part=" + video_part + ", video_id=" + video_id
				+ ", video_url=" + video_url + ", video_channelName=" + video_channelName + ", video_img=" + video_img
				+ ", video_viewCnt=" + video_viewCnt + ", video_zzimCnt=" + video_zzimCnt + "]";
	}






}