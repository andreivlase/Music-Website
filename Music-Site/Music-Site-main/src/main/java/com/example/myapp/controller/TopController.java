package com.example.myapp.controller;

import com.example.myapp.dto.AlbumRatingDTO;
import com.example.myapp.repository.RatingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TopController {

    @Autowired
    private RatingRepository ratingRepo;

    @GetMapping("/tops")
    public String showTopPage(Model model) {
        Pageable top5 = PageRequest.of(0, 5);

        List<AlbumRatingDTO> rockTop = ratingRepo.findTopAlbumsByGenre("rock", top5);
        List<AlbumRatingDTO> hiphopTop = ratingRepo.findTopAlbumsByGenre("hip hop", top5);
        List<AlbumRatingDTO> popTop = ratingRepo.findTopAlbumsByGenre("pop", top5);

        model.addAttribute("rockTop", rockTop);
        model.addAttribute("hiphopTop", hiphopTop);
        model.addAttribute("popTop", popTop);

        return "tops";
    }
}
