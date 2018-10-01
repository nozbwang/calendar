package com.zbwang.calendar.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zbwang.calendar.domain.Voice;

public interface IVoiceDao {

	List<Voice> listVoices();

	void insertVoice(Voice faceVoice);

	List<Voice> getLatestVoice(int startRow, int endRow, String belongType, Integer belongId);

	void deleteVoice(Integer voiceId, Integer userId);

	void updateVoice(Voice faceVoice);

	Voice getVoiceById(int voiceId);

	List<Voice> getVisibleSecret(int startRow, int endRow, Integer userId);

	List<Voice> getCommentByVoiceIdGroup(@Param("voiceIds") List<Integer> voiceIds);
}