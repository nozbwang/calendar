package com.zbwang.calendar.controller;

import com.google.common.collect.Maps;
import com.zbwang.calendar.constant.BelongTypeEnum;
import com.zbwang.calendar.service.IAttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by bobomeilin on 2018/8/18.
 */
@Controller
@RequestMapping("galary")
public class GalaryController extends AbstractController {

    @Autowired
    private IAttachService attachService;

    @RequestMapping("")
    public ModelAndView showGalary() {
        return getModelAndView("belongTypeEnums", BelongTypeEnum.values(), "pages/galary");
    }

    @RequestMapping("/add")
    public ModelAndView addGalary(String belongType) {
        return getModelAndView("belongTypeEnum", BelongTypeEnum.getBelongTypeEnum(belongType), "pages/galary_add");
    }

    @RequestMapping("/detail")
    public ModelAndView galaryDetail(String belongType) {
        List<Long> attachIds = attachService.listAttachIds(belongType);
        Map<String,Object> map = Maps.newHashMapWithExpectedSize(2);
        map.put("attachIds",attachIds);
        map.put("belongTypeEnum", BelongTypeEnum.getBelongTypeEnum(belongType));
        return getBaseModelAndView(map, "pages/galary_detail");
    }
}
