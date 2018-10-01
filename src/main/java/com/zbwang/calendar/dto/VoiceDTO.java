package com.zbwang.calendar.dto;

import org.apache.commons.lang.StringUtils;

import java.util.Objects;

/**
 * Created by bobomeilin on 2018/8/17.
 */
public class VoiceDTO {

    public boolean isInValid(){
        return StringUtils.isBlank(voice) && Objects.isNull(attachId);
    }

    public boolean hasAttach(){
        return Objects.nonNull(attachId);
    }

    private String voice;

    private Integer attachId;

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public Integer getAttachId() {
        return attachId;
    }

    public void setAttachId(Integer attachId) {
        this.attachId = attachId;
    }

    @Override
    public String toString() {
        return "VoiceDTO{" +
                "voice='" + voice + '\'' +
                ", attachId=" + attachId +
                '}';
    }
}
