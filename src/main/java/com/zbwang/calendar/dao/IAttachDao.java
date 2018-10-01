package com.zbwang.calendar.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zbwang.calendar.domain.Attach;

public interface IAttachDao {

	Attach getAttachByAttachId(Integer attachId);

	List<Long> listBelongAttachIds(@Param("belongType") String belongType);

	List<Attach> getAttachByBelongIdGroup(@Param("belongType") String belongType, @Param("belongIds") List<Integer> belongIds);

	void insertVoiceAttach(Attach faceVoiceAttach);

	void updateAttachBelong(Attach faceVoiceAttach);

	void deleteLocalPicture(Attach faceVoiceAttach);
}
