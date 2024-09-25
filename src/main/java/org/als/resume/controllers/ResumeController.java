package org.als.resume.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/resume")
public class ResumeController {

    @GetMapping(value = "{resumeId}")
    public ModelAndView getResume(@PathVariable Integer resumeId, @PathVariable Integer languageId){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("resume");
        mv.getModel().put("data", );
    }

}
