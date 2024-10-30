package org.als.resume.controllers;

import org.als.resume.DictionaryHelper;
import org.als.resume.entities.Email;
import org.als.resume.entities.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/form")
public class GenericFormController {

    @GetMapping("/{dictionaryName}")
    public ModelAndView renderGenericForm(@PathVariable String dictionaryName){
        var genericFormView = new ModelAndView("generic_form");
        var dictionary = new DictionaryHelper(Email.getTestEmail());
        genericFormView.addObject("user", Users.getTestUser());
        genericFormView.addObject( "dictionary", dictionary );

        return genericFormView;

    }
}
