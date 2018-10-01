package com.zbwang.calendar.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zbwang.calendar.constant.Constants;
import com.zbwang.calendar.dao.IVoiceDao;
import com.zbwang.calendar.domain.Voice;
import com.zbwang.calendar.service.IVoiceService;

@Service
public class VoiceServiceImpl implements IVoiceService {

	@Autowired
	private IVoiceDao faceVociceDao;

	@Override
	public List<Voice> listVoices() {
		return faceVociceDao.listVoices();
	}

	@Override
	public void preapareComment(List<Voice> voices) {
		List<Integer> voiceIdGroup = voices.stream().map(i -> i.getVoiceId()).collect(Collectors.toList());
		List<Voice> comments = faceVociceDao.getCommentByVoiceIdGroup(voiceIdGroup);
		Map<Integer, List<Voice>> commentMap = comments.stream().collect(Collectors.groupingBy(v -> v.getBelongId()));
		for (Voice faceVoice : voices) {
			faceVoice.setComments(commentMap.get(faceVoice.getVoiceId()));
		}
	}

	@Override
	public Integer insertVoice(Integer userId, String voice) {
		Voice faceVoice = new Voice();
		faceVoice.setVoice(voice);
		faceVoice.setUserId(userId);
		faceVoice.setBelongId(0);
		faceVoice.setBelongType(Constants.B_TYPE_VOICE);
		faceVociceDao.insertVoice(faceVoice);
		return faceVoice.getVoiceId();
	}

	@Override
	public Integer insertComment(Integer userId, Integer voiceId, String comment) {
		Voice faceVoice = new Voice();
		faceVoice.setVoice(comment);
		faceVoice.setUserId(userId);
		faceVoice.setBelongId(voiceId);
		faceVoice.setBelongType(Constants.B_TYPE_COMMENT);
		faceVociceDao.insertVoice(faceVoice);
		return faceVoice.getVoiceId();
	}
}
