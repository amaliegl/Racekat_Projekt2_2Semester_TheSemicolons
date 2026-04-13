package org.example.racekat_projekt2_2semester_thesemicolons.ui;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.racekat_projekt2_2semester_thesemicolons.model.*;
import org.example.racekat_projekt2_2semester_thesemicolons.service.CatService;
import org.example.racekat_projekt2_2semester_thesemicolons.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Book;
import java.time.LocalDate;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CatService catService;

    @GetMapping("/")
    public String getDefaultPage() {
        System.out.println("Er inde i GetMapping");
        /*User user = new User("Jytte", "jytte@kat.dk", "1234", Role_ENUM.Member, "87654321");
        Cat cat = new Cat("Mille", LocalDate.now(), Color_ENUM.Lilac, Sex_ENUM.Female, false, true, "placeholder.jpg", "placeholder.jpg");
        System.out.println("Har oprettet bruger og kat");

        User savedUser = userService.createUser(user);
        System.out.println("Bruger gemt i database: " + savedUser);
        catService.createCat(cat, user);
*/

        System.out.println("Alle brugere fra databasen: " + userService.getAllUsers());
        //System.out.println("Jyttes liste af katte: " + user.getCats());

        return "test";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpSession session, Model model) {
        //User loggedIn = userService.login(user.getEmail(), user.getPassword()); TODO - udkommenteret for test
        //TODO -nedenstående er til test
        User loggedIn = userService.findByEmail("jytte@kat.dk");

        if (loggedIn != null) {
            session.setAttribute("currentUser", loggedIn);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Forkert brugernavn eller adgangskode.");
            return "login";
        }
    }

    @GetMapping("/home")
    public String getHomePage() {

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

    @GetMapping("/profile")
    public String getProfilePage() {

        return "profile";
    }

    @PostMapping()
    public String logoutUser() {

        return "login";
    }
}
