package com.UserDB.Controller;

import com.UserDB.Dao.RegisterDao;
import com.UserDB.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.sql.SQLException;

import static java.lang.String.join;

@Controller
@RequestMapping("/register")

public class RegisterController {
    @RequestMapping(value = "/registerForm",method = RequestMethod.GET)
    public String register(Model model){
        User user=new User();
        model.addAttribute("user", user);
        return "register";
    }

    @RequestMapping(value = "/registerAddUser",method = RequestMethod.POST)
    public String showHome(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws SQLException {
        if (!user.getPassword().equals(user.getcPassword())) {
            httpServletRequest.setAttribute("error", true);
         return "/register";
        } else if (bindingResult.hasErrors()) {
            return "/register";
        } else {
            String name = user.getName();
//            String email = user.getEmail();
//            String password=user.getPassword();
//            String gender= user.getGender();
//            String state=user.getState();
            String[] languages = user.getLanguage();
            String selectedLanguagesString = join(",", languages);
//            String qualification= user.getQualification().get(0);
//            System.out.println(qualification);
            RegisterDao registerDao = new RegisterDao();
            registerDao.addUser(user, selectedLanguagesString);
            return "index";
        }

    }
}
