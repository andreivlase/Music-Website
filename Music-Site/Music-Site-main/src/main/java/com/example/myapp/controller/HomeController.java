package com.example.myapp.controller;

import com.example.myapp.repository.AlbumRepository;
import com.example.myapp.entity.Album;
import com.example.myapp.repository.RatingRepository;
import com.example.myapp.dto.AlbumRatingDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Controller
public class HomeController {

    private final AlbumRepository albumRepo;

    public HomeController(AlbumRepository albumRepo) {
        this.albumRepo = albumRepo;
    }

    @Autowired
    private RatingRepository ratingRepo;

    @GetMapping("/")
    public String showHome(Model model) {
        List<AlbumRatingDTO> topAlbums = ratingRepo.findTopAlbumsByAvgRating(PageRequest.of(0, 10));
        List<AlbumRatingDTO> latestAlbums = ratingRepo.findLatestAlbums(PageRequest.of(0, 10));

        model.addAttribute("topAlbums", topAlbums);
        model.addAttribute("latestAlbums", latestAlbums);

        return "home";
    }


    @GetMapping("/contact")
    public String showContactPage() {
        return "contact";
    }

}
