package org.als.resume.controllers;

import org.als.resume.config.ResumeDBCacheConfig;
import org.als.resume.entities.Users;
import org.als.resume.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

@Controller
@RequestMapping(value = "/resume")
public class ResumeController {
    @Autowired
    private UserService service;

    @GetMapping("/{userId}/{templateId}/{languageId}")
    public ModelAndView getResume(@PathVariable("userId") Integer userId, @PathVariable("templateId") Integer templateId,
                                  @PathVariable("languageId") Integer languageId) {

        Users user = service.findUserById(userId);
        if( Objects.isNull(user) ){
            user = Users.getTestUser();
        }
        ModelAndView mv = new ModelAndView("resume");
        mv.addObject("user", user);
        mv.addObject("language", languageId);

        return mv;
    }

    @GetMapping("/template/{templateId}/{languageId}")
    @Cacheable( value = ResumeDBCacheConfig.CACHE_NAME)
    public ModelAndView getResumeTemplate( @PathVariable("templateId") Integer templateId, @PathVariable("languageId") Integer languageId ) {
        Users testUser = Users.getTestUser();

        ModelAndView mv = new ModelAndView("resume");
        mv.addObject("user", testUser);
        mv.addObject("language", languageId);
        mv.addObject("template", templateId);

        return mv;
    }
}
