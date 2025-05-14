package com.example.myapp.controller;

import com.example.myapp.entity.Album;
import com.example.myapp.entity.Review;
import com.example.myapp.entity.User;
import com.example.myapp.repository.AlbumRepository;
import com.example.myapp.repository.RatingRepository;
import com.example.myapp.repository.ReviewRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @GetMapping
    @ResponseBody
    public List<Album> getAll() {
        return albumRepository.findAll();
    }

    @GetMapping("/search")
    public String searchAlbums(@RequestParam("query") String query, Model model) {
        List<Album> results = albumRepository.findByTitleContainingIgnoreCase(query);
        model.addAttribute("results", results);
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/{id}")
    public String getAlbumPage(@PathVariable int id, Model model, HttpSession session) {
        Optional<Album> albumOpt = albumRepository.findById(id);
        if (albumOpt.isEmpty()) return "redirect:/albums";

        Album album = albumOpt.get();
        List<Review> reviews = reviewRepository.findByIdAlbum(id);
        Double avgRating = ratingRepository.calculateAverageRating(id);

        model.addAttribute("album", album);
        model.addAttribute("reviews", reviews);
        model.addAttribute("avgRating", avgRating);

        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            model.addAttribute("userId", user.getId_user());
        }

        return "album";
    }


}
