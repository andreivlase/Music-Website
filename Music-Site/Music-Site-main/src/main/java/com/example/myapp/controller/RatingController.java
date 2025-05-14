package com.example.myapp.controller;

import com.example.myapp.entity.Rating;
import com.example.myapp.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;

    @PostMapping("/add")
    public String addRating(@RequestParam int id_user,
                            @RequestParam int id_album,
                            @RequestParam int rating) {
        Rating r = new Rating();
        r.setIdUser(id_user);
        r.setIdAlbum(id_album);
        r.setRating(rating);

        ratingRepository.save(r);
        return "redirect:/albums/" + id_album;
    }
}
