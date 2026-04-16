package org.example.racekat_projekt2_2semester_thesemicolons.ui;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.racekat_projekt2_2semester_thesemicolons.model.*;
import org.example.racekat_projekt2_2semester_thesemicolons.service.CatService;
import org.example.racekat_projekt2_2semester_thesemicolons.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.awt.print.Book;
import java.time.LocalDate;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CatService catService;

    @GetMapping("/")
    public String getDefaultPage(Model model, HttpSession session, SessionStatus sessionStatus) {
        User user = (User) session.getAttribute("currentUser");
        model.addAttribute("loggedInUser", user);
        return "home";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpSession session, Model model) {
        User loggedIn = userService.login(user.getEmail(), user.getPassword());

        //TODO - Nedenstående er til test
        //User loggedIn = userService.findByEmail("jytte@kat.dk");

        if (loggedIn != null) {
            session.setAttribute("currentUser", loggedIn);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Forkert brugernavn eller adgangskode.");
            return "login";
        }
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, HttpSession session, Model model) {
        User createdUser = userService.createUser(user);
        if (createdUser != null) {
            session.setAttribute("currentUser", createdUser);
            //TODO - ovenstående var ikke med i Mikkels, har selv tilføjet, så man er logget ind, når man opretter sig
            return "redirect:/home"; //var originalt "redirect:/login"
        } else {
            model.addAttribute("error", "Email er allerede i brug."); //TODO - spørg til "brugernavn/adgangskode forker" ift. dette
            return "login";
        }
    }


    @GetMapping("/home")
    public String getHomePage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        model.addAttribute("loggedInUser", user);
        return "home";
    }

    @GetMapping("/findMember")
    public String getFindMemberPage() {

        return "findMember";
    }

    @GetMapping("/about")
    public String getAboutPage() {

        return "about";
    }

    @GetMapping("/myCats")
    public String getMyCatsPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("cats", user.getCats());
        return "myCats";
    }

    @GetMapping("/myCats/addCat")
    public String showAddCatForm(Model model) {
        model.addAttribute("newCat", new Cat());
        return "addCat";
    }

    @PostMapping("/myCats/addCat")
    public String submitAddCatForm(@ModelAttribute Cat cat, HttpSession session) {
        catService.createCat(cat, (User) session.getAttribute("currentUser"));
        return "redirect:/myCats";
    }

    @GetMapping("/myCats/editCat/{id}")
    public String showEditCatForm(@PathVariable int id, Model model ){
        Cat cat = catService.findCatById(id);
        model.addAttribute("cat", cat);
        return "editCat";
    }

    @PostMapping("/myCats/editCat")
    public String submitEditCatForm(@ModelAttribute Cat cat, HttpSession session){
        catService.editCat(cat);
        refreshCurrentSessionUser(session); //To fetch the user's cats from the database again so the update will show on the website
        return "redirect:/myCats";
    }

    @PostMapping("/myCats/editCat/delete/{id}")
    public String submitDeleteCatForm(@PathVariable int id, HttpSession session){
        catService.deleteCat(id);
        refreshCurrentSessionUser(session); //To fetch the user's cats from the database again so the update will show on the website
        return "redirect:/myCats";
    }

    @GetMapping("/profile")
    public String getProfilePage(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("currentUser"));
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String showEditProfileForm(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("currentUser"));
        return "editProfile";
    }

    @PostMapping("/profile/edit")
    public String submitEditProfileForm(@ModelAttribute User user, HttpSession session) {
        User editedUser = userService.editUser(user, (User) session.getAttribute("currentUser"));
        session.setAttribute("currentUser", editedUser);
        return "redirect:/profile";
        //TODO - hvordan kommer vi rundt om at opdatere bruger, når ikke alle informationer må opdateres?
    }

    @PostMapping("/profile/edit/delete/{id}")
    public String submitDeleteUserForm(@PathVariable int id, HttpSession session){
        userService.deleteUser(id);
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    private void refreshCurrentSessionUser(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        session.setAttribute("currentUser", userService.findByExistingId(user.getId()));
    }
}
