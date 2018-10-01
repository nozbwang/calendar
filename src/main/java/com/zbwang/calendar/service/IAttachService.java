package com.zbwang.calendar.service;

import java.io.IOException;
import java.util.List;

import com.zbwang.calendar.domain.User;
import com.zbwang.calendar.dto.AttachDTO;
import com.zbwang.calendar.dto.VoiceDTO;
import org.apache.commons.fileupload.FileItem;

import com.zbwang.calendar.domain.Attach;
import com.zbwang.calendar.domain.Book;
import com.zbwang.calendar.domain.Voice;

public interface IAttachService {

	Attach getAttachByAttachId(Integer attachId);

    List<Long> listAttachIds(String belongType);

    void prepareVoiceAttach(List<Voice> latestVoiceGroup);

	void prepareBookAttach(List<Book> books);

	Integer insertAttach(AttachDTO attachDTO, User user) throws IOException;

    void insertLocalPicture(FileItem localPicture, String belongType, User user);

    void updateAttachBelong(Integer attachId, String belongType, Integer belongId, User user);

	void deleteLocalPicture(Integer userId, String belongType, int belongId);

}