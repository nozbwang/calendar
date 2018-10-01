package com.zbwang.calendar.service.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.zbwang.calendar.domain.User;
import com.zbwang.calendar.dto.AttachDTO;
import com.zbwang.calendar.util.CommonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zbwang.calendar.constant.Constants;
import com.zbwang.calendar.dao.IAttachDao;
import com.zbwang.calendar.domain.Attach;
import com.zbwang.calendar.domain.Book;
import com.zbwang.calendar.domain.Voice;
import com.zbwang.calendar.service.IAttachService;

@Service
public class AttachServiceImpl implements IAttachService {

    @Autowired
    private IAttachDao faceVoiceAttachDao;

    @Override
    public Attach getAttachByAttachId(Integer attachId) {
        return faceVoiceAttachDao.getAttachByAttachId(attachId);
    }

    @Override
    public List<Long> listAttachIds(String belongType){
        return faceVoiceAttachDao.listBelongAttachIds(belongType);
    }

    @Override
    public void prepareVoiceAttach(List<Voice> latestVoiceGroup) {
        List<Integer> voiceIdGroup = latestVoiceGroup.stream().map(i -> i.getVoiceId()).collect(Collectors.toList());
        Map<Integer, List<Attach>> faceVoiceAttachMap = getAttachByBelongIdGroup(Constants.B_TYPE_VOICE, voiceIdGroup);
        for (Voice faceVoice : latestVoiceGroup) {
            faceVoice.setFaceVoiceAttachs(faceVoiceAttachMap.get(faceVoice.getVoiceId()));
        }
    }

    @Override
    public void prepareBookAttach(List<Book> books) {
        List<Integer> bookIdGroup = books.stream().map(b -> b.getBookId()).collect(Collectors.toList());
        Map<Integer, Attach> faceVoiceAttachMap = getSingleAttachByBelongIdGroup(Constants.B_TYPE_BOOK, bookIdGroup);
        for (Book book : books) {
            book.setCover(faceVoiceAttachMap.get(book.getBookId()));
        }
    }

    public Map<Integer, List<Attach>> getAttachByBelongIdGroup(String belongType, List<Integer> belongIds) {
        if (CollectionUtils.isEmpty(belongIds)) {
            return Collections.emptyMap();
        }
        List<Attach> faceVoiceAttachGroup = faceVoiceAttachDao.getAttachByBelongIdGroup(belongType, belongIds);
        return faceVoiceAttachGroup.stream().collect(Collectors.groupingBy(Attach::getBelongId));
    }

    public Map<Integer, Attach> getSingleAttachByBelongIdGroup(String belongType, List<Integer> belongIds) {
        if (CollectionUtils.isEmpty(belongIds)) {
            return Collections.emptyMap();
        }
        List<Attach> faceVoiceAttachGroup = faceVoiceAttachDao.getAttachByBelongIdGroup(belongType, belongIds);
        return faceVoiceAttachGroup.stream().collect(Collectors.toMap(Attach::getBelongId, a -> a, (k1, k2) -> k1));
    }

    @Override
    public Integer insertAttach(AttachDTO attachDTO, User user) throws IOException {
        Attach faceVoiceAttach = new Attach();
        faceVoiceAttach.setBelongId(null);
        faceVoiceAttach.setBelongType(attachDTO.getBelongType());
        faceVoiceAttach.setUserId(user.getUserId());
        faceVoiceAttach.setAttachmentName(attachDTO.getFileName());
        faceVoiceAttach.setSize(attachDTO.getFileLen());
        faceVoiceAttach.setType("image/jpeg");
        faceVoiceAttach.setContent(attachDTO.getFile().getBytes());
        faceVoiceAttachDao.insertVoiceAttach(faceVoiceAttach);
        return faceVoiceAttach.getAttachmentId();
    }

    @Override
    public void insertLocalPicture(FileItem localPicture, String belongType, User user) {
        if (localPicture != null) {
            Attach faceVoiceAttach = new Attach();
            faceVoiceAttach.setBelongType(belongType);
            faceVoiceAttach.setUserId(user.getUserId());
            faceVoiceAttach.setAttachmentName(CommonUtil.getPhotoNameLimited(localPicture.getName()));
            faceVoiceAttach.setSize(localPicture.getSize());
            faceVoiceAttach.setType(localPicture.getContentType());
            faceVoiceAttach.setContent(localPicture.get());
            localPicture.delete();
            faceVoiceAttachDao.insertVoiceAttach(faceVoiceAttach);
        }
    }

    @Override
    public void updateAttachBelong(Integer attachId, String belongType, Integer belongId, User user){
        Attach faceVoiceAttach = new Attach();
        faceVoiceAttach.setBelongId(belongId);
        faceVoiceAttach.setUserId(user.getUserId());
        faceVoiceAttach.setAttachmentId(attachId);
        faceVoiceAttach.setBelongType(belongType);
        faceVoiceAttachDao.updateAttachBelong(faceVoiceAttach);
    }

    @Override
    public void deleteLocalPicture(Integer userId, String belongType, int belongId) {
        Attach faceVoiceAttach = new Attach();
        faceVoiceAttach.setBelongId(belongId);
        faceVoiceAttach.setBelongType(belongType);
        faceVoiceAttach.setUserId(userId);
        faceVoiceAttachDao.deleteLocalPicture(faceVoiceAttach);
    }

}
