package com.nic.pms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nic.pms.model.User;
import com.nic.pms.service.UserService;

 
@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	 @Autowired
	 UserService userService;
	 
	 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	 
	@GetMapping({"/","/login"})
	public ModelAndView login(ModelAndView modelAndView, User user) {
		logger.info("this is  login");
		logger.debug("This is a debug message");
		logger.info("This is an info message");
		logger.warn("This is a warn message");
		logger.error("This is an error message");
		modelAndView.addObject("user", user);
		modelAndView.setViewName("login");
		return modelAndView;
		
	}
	
	
	  @GetMapping("/home") 
	  public ModelAndView loginProcess() {
	  ModelAndView model = new ModelAndView(); model.setViewName("home");
	  return model;
	  
	  }
	 
	
	 
	
	@PostMapping("/login")
    public ModelAndView loginUser(ModelAndView modelAndView, User user) {
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser != null) {

            if (encoder.matches(user.getPassword(), existingUser.getPassword())){
                modelAndView.addObject("message", "Successfully logged in!");
                modelAndView.setViewName("home");
            } else {
                // Wrong password
                modelAndView.addObject("message", "Incorrect password. Try again.");
                modelAndView.setViewName("login");
            }
        } else {
            modelAndView.addObject("message", "The user provided does not exist!");
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }
}
