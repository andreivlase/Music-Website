package com.example.myapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Ratings")
@IdClass(RatingId.class)
public class Rating {

    @Id
    @Column(name = "id_user")
    private int idUser;

    @Id
    @Column(name = "id_album")
    private int idAlbum;

    private int rating;


    @ManyToOne
    @JoinColumn(name = "id_album", insertable = false, updatable = false)
    private Album album;


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
