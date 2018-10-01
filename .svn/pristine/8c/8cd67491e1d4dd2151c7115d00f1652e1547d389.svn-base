package com.zbwang.calendar.domain;

import java.util.Date;

import com.zbwang.calendar.constant.Constants;

public class AttachLink {

	private Integer attachmentLinkId;
	private Integer belongId;
	private String belongType;
	private Integer userId;
	private String type;
	private String musicType;
	private String musicId;
	private String link;
	private Date addTime;

	public Integer getAttachmentLinkId() {
		return attachmentLinkId;
	}

	public void setAttachmentLinkId(Integer attachmentLinkId) {
		this.attachmentLinkId = attachmentLinkId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMusicType() {
		return musicType;
	}

	public void setMusicType(String musicType) {
		this.musicType = musicType;
	}

	public String getMusicId() {
		return musicId;
	}

	public void setMusicId(String musicId) {
		this.musicId = musicId;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public boolean isMusicLink() {
		return Constants.LINK_MUSIC.equals(type);
	}

	public boolean isPictureLink() {
		return Constants.LINK_PICTURE.equals(type);
	}

	public boolean is163Music() {
		return isMusicLink() && Constants.MUSIC_163.equals(musicType);
	}

	public boolean isXiamiMusic() {
		return isMusicLink() && Constants.MUSIC_XIAMI.equals(musicType);
	}

	public Integer getBelongId() {
		return belongId;
	}

	public void setBelongId(Integer belongId) {
		this.belongId = belongId;
	}

	public String getBelongType() {
		return belongType;
	}

	public void setBelongType(String belongType) {
		this.belongType = belongType;
	}
}