package com.kernohad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.*;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by user on 5/24/2017.
 */
@Controller // This means that this class is a controller
@RequestMapping(path="/demo")   // This means URL's start with /demo (after Application path)
public class MainController {
    @Autowired  // This means to get the bean called 'userRepository'
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;
    private int USERS_ON_PAGE = 10;



    @RequestMapping("/")
    public String index(@ModelAttribute User user, Model model){
        model.addAttribute("user", user);
        return "form";
    }


    @RequestMapping(path="/add", method=RequestMethod.POST)    // Map ONLY POST Requests
    public String addNewUser (@ModelAttribute User user, Model model, RedirectAttributes redirectAttrs) {
        // @ResponseBody means the returned String is the response, not a view name
        model.addAttribute("user", user);
        validate(user, redirectAttrs);
        return "redirect:/demo/";   // Redirects to the RequestMapping"/" which calls the form.html

    }

    @RequestMapping(path="/edit", method=RequestMethod.POST)    // Map ONLY POST Requests
    public @ResponseBody String editUser (@ModelAttribute User user, Model model, RedirectAttributes redirectAttrs) {
        // @ResponseBody means the returned String is the response, not a view name

        model.addAttribute("user", user);
        if(validate(user, redirectAttrs))
            return "success";
        return redirectAttrs.getFlashAttributes().get("message").toString();
    }

    @RequestMapping(path="/remove/{id}", method=RequestMethod.POST)    // Map ONLY POST Requests
    public String removeUser (@PathVariable Long id, RedirectAttributes redirectAttrs) {       // RedirectAttribute sends an attribute to the next model made.
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        redirectAttrs.addFlashAttribute("message", "User Removed");                                        // flash attributes are automatically added to the model of the controller that serves the target URL.
        userRepository.delete(userRepository.findOne(id));
        return "redirect:/demo/";   // Redirects to the RequestMapping"/" which calls the form.html
    }


    @RequestMapping(path="/all", method=RequestMethod.GET)
    public  String getAllUsers(int pageNumber,Model model) {
        Page<User> users = userRepository.findAll(new PageRequest(pageNumber - 1,USERS_ON_PAGE));
        model.addAttribute("user", new User());
        model.addAttribute("list", users);
        model.addAttribute("numberOfPages", numberOfPages());
        model.addAttribute("tag", "all");

        return "table";
    }



    @RequestMapping(path="/search")
    public  String searchUsers(int pageNumber, @ModelAttribute User user, Model model) {

        model.addAttribute("numberOfPages", 1);
        model.addAttribute("user", new User());
        model.addAttribute("list", userRepository.search(user.getName(), user.getEmail(), user.getId(), new PageRequest(pageNumber - 1, USERS_ON_PAGE)));
        model.addAttribute("numberOfPages", numberOfPages());
        model.addAttribute("tag", "search");
        return "table";

    }




    private boolean validate(@Valid User user, RedirectAttributes redirectAttrs){
        UserValidator validator = new UserValidator();
        EmailValidator emailValidator = new EmailValidator();


        //http://www.baeldung.com/javax-validation
        //=========================================================================================================
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator beanValidator = factory.getValidator();

        Set<ConstraintViolation<User>> violations = beanValidator.validate(user);

        String message = "";
        if(violations.size() > 0){
            for (ConstraintViolation<User> violation : violations) {
                message = message + "Error: " + violation.getMessage();
            }
            redirectAttrs.addFlashAttribute("message", message);
            redirectAttrs.addFlashAttribute(user);
            return false;
        }
        //=========================================================================================================

        Errors errors = new BeanPropertyBindingResult(user, "objectName");
        validator.validate(user, errors);
        if(errors.getErrorCount() > 0){
            if (errors.hasFieldErrors("name")){
                redirectAttrs.addFlashAttribute("message", "Error: 'Name' is empty.");
                redirectAttrs.addFlashAttribute(user);
                return false;
            }
            else if (errors.hasFieldErrors("email")){
                redirectAttrs.addFlashAttribute("message", "Error: 'Email' is empty.");
                redirectAttrs.addFlashAttribute(user);
                return false;
            }


        }
        else if (!emailValidator.validate(user.getEmail())){
            redirectAttrs.addFlashAttribute("message", "Error: Not a valid email. Ex: valid@em.ail");
            redirectAttrs.addFlashAttribute(user);
            return false;
        }
        else{
            userRepository.save(user);
            redirectAttrs.addFlashAttribute("message", "User Saved");

        }
        return true;

    }

    private double numberOfPages(){

        double numberOfPages = (double) userRepository.count()/USERS_ON_PAGE;


        return numberOfPages + 1;
    }
}
