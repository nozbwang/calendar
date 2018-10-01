package com.zbwang.calendar.service;

import java.util.List;

import com.zbwang.calendar.domain.Voice;

public interface IVoiceService {

	List<Voice> listVoices();

	Integer insertVoice(Integer userId, String voice);

	Integer insertComment(Integer userId, Integer voiceId, String comment);

	void preapareComment(List<Voice> voices);
}
