package com.zbwang.calendar.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;
import com.zbwang.calendar.constant.Constants;
import com.zbwang.calendar.dto.AttachDTO;
import com.zbwang.calendar.util.UrlUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zbwang.calendar.domain.Attach;
import com.zbwang.calendar.service.IAttachService;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/attach")
public class PictureController extends AbstractController {

    @Autowired
    private IAttachService faceVoiceAttachService;

    @RequestMapping("/{attachId}/*")
    public void getAttach(@PathVariable Integer attachId, HttpServletResponse response) throws Exception {
        Attach faceVoiceAttach = faceVoiceAttachService.getAttachByAttachId(attachId);
        if (faceVoiceAttach != null) {
            response.setContentType("image/png");
            writeToBrowser(response, faceVoiceAttach.getContent());
        }
    }

    @RequestMapping("/upload")
    public void uploadAttach(AttachDTO attach, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer attachId = faceVoiceAttachService.insertAttach(attach, getUser());
        if (attachId != null) {
            Map<String, Object> result = Maps.newHashMapWithExpectedSize(2);
            result.put("id", attachId);
            result.put("url", UrlUtils.getAttachUrl(attachId, attach.getFileName()));
            responseAjax(response, true, result);
            return;
        }
        responseAjax(response, false);
    }

    @RequestMapping("/uploadOriginal")
    public void uploadAttach(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
        CommonsMultipartFile file = (CommonsMultipartFile) request.getFile("file");
        String belongType = request.getParameter("belongType");
        if (file != null && file.getSize() != 0) {
            faceVoiceAttachService.insertLocalPicture(file.getFileItem(), belongType, getUser());
        }
        responseAjax(response, true);
    }

    private void writeToBrowser(HttpServletResponse response, byte[] content) throws IOException {
        ByteArrayInputStream in = null;
        OutputStream out = null;
        try {
            in = new ByteArrayInputStream(content);
            out = response.getOutputStream();
            byte buffer[] = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }
    }

}
