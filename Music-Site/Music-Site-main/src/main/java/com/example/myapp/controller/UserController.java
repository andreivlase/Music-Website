package com.example.myapp.controller;

import com.example.myapp.entity.User;
import com.example.myapp.entity.Rating;
import com.example.myapp.entity.Review;
import com.example.myapp.repository.UserRepository;
import com.example.myapp.repository.RatingRepository;
import com.example.myapp.repository.ReviewRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RatingRepository ratingRepo;

    @Autowired
    private ReviewRepository reviewRepo;

    @ResponseBody
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


    @GetMapping("/user")
    public String userProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        int userId = user.getId_user();
        List<Rating> userRatings = ratingRepo.findByIdUser(userId);
        List<Review> userReviews = reviewRepo.findByIdUser(userId);

        model.addAttribute("user", user);
        model.addAttribute("ratings", userRatings);
        model.addAttribute("reviews", userReviews);

        return "user";
    }
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String handleSignup(@ModelAttribute("user") User user, Model model) {
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("signupError", "Username already exists!");
            return "signup";
        }

        user.setRole("user");
        userRepo.save(user);

        return "redirect:/login";
    }

}
