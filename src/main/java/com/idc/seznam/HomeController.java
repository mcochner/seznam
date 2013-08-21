package com.idc.seznam;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.idc.seznam.hibernate.User;
import com.idc.seznam.hibernate.UserServiceInterface;
import com.idc.seznam.hibernate.UserType;
import com.idc.seznam.hibernate.UserValidator;

/**
 * Handles requests for the application home page.
 */
/**
 * @author mcochner
 * 
 */

@Controller
public class HomeController {

	@Autowired
	private UserServiceInterface usersService;
	
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
		binder.setValidator(new UserValidator());
	}

	@ModelAttribute("user")
	public User createOrReturnTheRightUser(
			@RequestParam(required = false) Integer uid, 
			@RequestParam(required = false) String firstName, 
			@RequestParam(required = false) String lastName ) {
		if (uid == null) {
			// logger.info("Jsem v createOrReturnTheRightUser null");
			return new User();
		}
		if (uid == 0) {
			// logger.info("Jsem v createOrReturnTheRightUser 0");
			return new User();

		} 			
		if ((firstName != null) && (firstName.isEmpty()))
			{
				logger.info("firstName Is empty but still ... what to do?");			
			}

		return usersService.getUserByUid(uid);
		
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	/**
	 * Main view lists all users
	 */
	@RequestMapping(value = { "/", "/main" }, method = RequestMethod.GET)
	public String page1(Model model) {
		// logger.info("Welcome GET main!");
		model.addAttribute("users", usersService.getUsers());
		return "main";
	}

	/**
	 * Same as previous, but also save the new user
	 */
	@RequestMapping(value = { "/", "/main" }, method = RequestMethod.POST)
	public String page2(@Validated @ModelAttribute("user") User user,
			BindingResult result, // binding result musi byt tesne ZA model
			// attribute
			Model model) {
		// logger.info("Welcome POST main!");

		if (result.hasErrors()) {
			logger.info("Something went Wrong Try again!!!");
			return "user";
		}

		// kdyz je uid==0 tak vytvori noveho usera,
		// kdyz uid!=0 tak se upravi stary user
		usersService.saveUser(user);
		//usersService.printUsers();
		
		return "redirect:main";
	}

	@RequestMapping(value = "/edit_user")
	public String page3() {
		// logger.info("Welcome edit_user!");
		// @ModelAttribute("user") 
		// createOrReturnTheRightUser(@RequestParam(required=false) Integer
		//

		return "user";
	}

	@RequestMapping(value = "/delete_user")
	public String page5(@RequestParam int uid, Model model) {
		// logger.info("GET: Welcome delete_user!");
		usersService.deleteUserByUid(uid);
		return page1(model);
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/test_populate", method = RequestMethod.POST)
	public String page6(Model model) {
		// logger.info("POST: Welcome test_populate!");

		try {

			User u = new User();
			u.setFirstName("Jiri");
			u.setLastName("Walker");
			u.setDateStarted(new Date(93, 10, 10));
			u.setType(UserType.EMPLOYEE);

			u.setUrl(new URL("http://www.idc.com"));

			usersService.saveUser(u);

			User u2 = new User();
			u2.setFirstName("Petr");
			u2.setLastName("Novak");
			u2.setDateStarted(new Date(103, 1, 1));
			u2.setType(UserType.CUSTOMER);
			u2.setUrl(new URL("http://www.seznam.cz"));
			usersService.saveUser(u2);

			User u3 = new User();
			u3.setFirstName("Oli");
			u3.setLastName("Olinnen");
			u3.setDateStarted(new Date(90, 5, 3));
			u3.setType(UserType.EMPLOYEE);
			u3.setUrl(new URL("http://www.google.com"));
			usersService.saveUser(u3);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return page1(model);
	}
}
