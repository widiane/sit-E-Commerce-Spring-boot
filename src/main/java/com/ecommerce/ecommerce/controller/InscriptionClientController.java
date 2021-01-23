package com.ecommerce.ecommerce.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.ecommerce.domaine.EmailConfig;

import com.ecommerce.ecommerce.domaine.UserRegistrationDto;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.service.UserImpl;

@Controller
@RequestMapping("/inscription")
public class InscriptionClientController {
	 	@Autowired
	    private UserImpl userService;

	 	
	 	private EmailConfig emailconf;

	    public InscriptionClientController(EmailConfig emailconf) {
			super();
			this.emailconf = emailconf;
		}




	    @ModelAttribute("user")

	    public UserRegistrationDto userRegistrationDto() {
	        return new UserRegistrationDto();
	    }

	    @GetMapping
	    public String afficherFormulaire(Model model) {
	        return "inscription";
	    }

	    @PostMapping
	    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
	        BindingResult result) {

	        User existing = userService.findByEmail(userDto.getEmail());
	        if (existing != null) {
	            result.rejectValue("email", null, "Un compte est déjà enregistré avec cette adresse e-mail");
	        }

	        if (result.hasErrors()) {
	            return "inscription";
	        }

	        userService.Register(userDto);

	        
	        JavaMailSenderImpl mailMessage = new JavaMailSenderImpl();
	        mailMessage.setHost(this.emailconf.getHost());
	        mailMessage.setPort(this.emailconf.getPort());
	        mailMessage.setUsername(this.emailconf.getUsername());
	        mailMessage.setPassword(this.emailconf.getPassword());
	        
	        SimpleMailMessage msg =new SimpleMailMessage();
	        msg.setFrom("noreply@sping.com");
	        msg.setTo(userDto.getEmail() );
	        msg.setSubject("subject");
	        msg.setText(  "Confirmation d'inscription  :\n"+ "username : " +userDto.getUsername() +"\n"+ " password : " + userDto.getPassword());
	        mailMessage.send(msg);
	        return "redirect:/login";


	    }
}
