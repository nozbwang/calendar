package com.zbwang.calendar.domain;

import com.zbwang.calendar.constant.Constants;

public class Attach {

	public String getAttachUrl() {
		return "/voice/attach/" + attachmentId + "/" + attachmentName + "/";
	}

	private Integer attachmentId;
	private Integer belongId;
	private String belongType;
	private Integer userId;
	private String attachmentName;
	private String type;
	private Long size;
	private Integer width;
	private Integer height;
	private byte[] content;

	public Integer getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public boolean isPicture() {
		return Constants.picContentTypes.contains(type);
	}

	public boolean isMusic() {
		return Constants.musicContentTypes.contains(type);
	}

	public boolean isVideo() {
		return Constants.videoContentTypes.contains(type);
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