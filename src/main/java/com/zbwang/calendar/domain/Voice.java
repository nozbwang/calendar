package com.zbwang.calendar.domain;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.zbwang.calendar.util.FormatUtil;

public class Voice {

	private Integer voiceId;
	private Integer userId;
	private String voice;
	private Date addTime;
	private Date updateTime;
	// 0：网站建议 1：读书 2:足迹
	private Integer belongId;
	private String belongType;
	private List<Attach> faceVoiceAttachs;
	private List<Voice> comments;
	private List<AttachLink> faceVoiceAttachLinks;

	public Integer getVoiceId() {
		return voiceId;
	}

	public void setVoiceId(Integer voiceId) {
		this.voiceId = voiceId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getVoice() {
		return voice;
	}

	public String getFormatVoice() {
		return voice == null ? voice : voice.replaceAll("\r\n", "</br>");
	}

	public void clearExtraLine() {
		voice = StringUtils.trimToEmpty(voice).replaceAll("(\r\n){1,}", "\r\n");
	}

	public boolean isNotEmpty() {
		return StringUtils.isNotBlank(voice);
	}

	public void setVoice(String voice) {
		this.voice = voice;
	}

	public Date getAddTime() {
		return addTime;
	}

	public String getFormattedAddTime() {
		return FormatUtil.formatMinuteTime(addTime);
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<Attach> getFaceVoiceAttachs() {
		return faceVoiceAttachs;
	}

	public void setFaceVoiceAttachs(List<Attach> faceVoiceAttachs) {
		this.faceVoiceAttachs = faceVoiceAttachs;
	}

	public List<AttachLink> getFaceVoiceAttachLinks() {
		return faceVoiceAttachLinks;
	}

	public void setFaceVoiceAttachLinks(List<AttachLink> faceVoiceAttachLinks) {
		this.faceVoiceAttachLinks = faceVoiceAttachLinks;
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

	public List<Voice> getComments() {
		return comments;
	}

	public void setComments(List<Voice> comments) {
		this.comments = comments;
	}
}