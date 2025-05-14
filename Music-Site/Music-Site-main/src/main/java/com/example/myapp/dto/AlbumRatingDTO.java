package com.example.myapp.dto;

import com.example.myapp.entity.Album;


public class AlbumRatingDTO {
    private Album album;
    private Double avgRating;

    public AlbumRatingDTO(Album album, Double avgRating) {
        this.album = album;
        this.avgRating = avgRating;
    }

    public Album getAlbum() { return album; }
    public Double getAvgRating() { return avgRating; }
}

