package com.zbwang.calendar.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zbwang.calendar.dto.VoiceDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zbwang.calendar.constant.Constants;
import com.zbwang.calendar.domain.Voice;
import com.zbwang.calendar.service.IAttachService;
import com.zbwang.calendar.service.IVoiceService;
import com.zbwang.calendar.util.FormatUtil;

import static com.zbwang.calendar.constant.Constants.B_TYPE_VOICE;

@Controller
@RequestMapping("/voice")
public class VoiceController extends AbstractController {

    @Autowired
    private IVoiceService faceVoiceService;
    @Autowired
    private IAttachService faceVoiceAttachService;

    @RequestMapping("")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Voice> latestVoiceGroup = faceVoiceService.listVoices();
        faceVoiceAttachService.prepareVoiceAttach(latestVoiceGroup);
        faceVoiceService.preapareComment(latestVoiceGroup);
        return getModelAndView("voices", latestVoiceGroup, "pages/voice");
    }

    @RequestMapping("/insertVoice")
    public String insertVoice(VoiceDTO voiceDTO, HttpServletResponse response) throws Exception {
        if (voiceDTO.isInValid()) {
            return redirectTo("/");
        }
        Integer userId = getUser().getUserId();
        Integer voiceId = faceVoiceService.insertVoice(userId, voiceDTO.getVoice());
        if (voiceDTO.hasAttach()) {
            faceVoiceAttachService.updateAttachBelong(voiceDTO.getAttachId(), B_TYPE_VOICE, voiceId, getUser());
        }
        return redirectTo("/");
    }

    @RequestMapping("/insertComment")
    public void insertVoiceComment(Integer voiceId, String comment, HttpServletResponse response) throws Exception {
        Integer userId = getUser().getUserId();
        faceVoiceService.insertComment(userId, voiceId, comment);
        responseAjax(response, true, FormatUtil.formatMinuteTime(new Date()));
    }
}
