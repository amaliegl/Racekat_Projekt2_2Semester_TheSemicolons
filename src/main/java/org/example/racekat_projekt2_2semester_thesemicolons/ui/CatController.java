package org.example.racekat_projekt2_2semester_thesemicolons.ui;

import jakarta.servlet.http.HttpSession;
import org.example.racekat_projekt2_2semester_thesemicolons.model.Cat;
import org.example.racekat_projekt2_2semester_thesemicolons.model.User;
import org.example.racekat_projekt2_2semester_thesemicolons.service.CatService;
import org.example.racekat_projekt2_2semester_thesemicolons.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CatController {

    @Autowired
    private UserService userService;

    @Autowired
    private CatService catService;

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
    public String showAddCatForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("newCat", new Cat());
        return "addCat";
    }

    @PostMapping("/myCats/addCat")
    public String submitAddCatForm(@ModelAttribute Cat cat, HttpSession session) {
        catService.createCat(cat, (User) session.getAttribute("currentUser"));
        return "redirect:/myCats";
    }

    @GetMapping("/myCats/editCat/{id}")
    public String showEditCatForm(@PathVariable int id, HttpSession session, Model model ){
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login";
        }
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

    private void refreshCurrentSessionUser(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        session.setAttribute("currentUser", userService.findByExistingId(user.getId()));
    }
}
