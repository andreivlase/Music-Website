package com.example.myapp.controller;

import com.example.myapp.entity.Review;
import com.example.myapp.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.myapp.entity.Review; // or Rating
import com.example.myapp.repository.ReviewRepository; // or RatingRepository
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository repo;

    @GetMapping
    public List<Review> getAll() {
        return repo.findAll();
    }

    @PostMapping("/add")
    public String addReview(@RequestParam int id_user,
                            @RequestParam int id_album,
                            @RequestParam String content) {
        Review r = new Review();
        r.setIdUser(id_user);
        r.setIdAlbum(id_album);
        r.setContent(content);
        r.setDate(java.time.LocalDateTime.now());

        repo.save(r);
        return "redirect:/albums/" + id_album;
    }
}
