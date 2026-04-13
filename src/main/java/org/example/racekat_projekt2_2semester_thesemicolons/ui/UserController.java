package org.example.racekat_projekt2_2semester_thesemicolons.ui;

import org.example.racekat_projekt2_2semester_thesemicolons.model.*;
import org.example.racekat_projekt2_2semester_thesemicolons.service.CatService;
import org.example.racekat_projekt2_2semester_thesemicolons.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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


}
