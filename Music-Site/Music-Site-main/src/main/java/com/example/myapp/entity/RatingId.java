package com.example.myapp.entity;

import java.io.Serializable;
import java.util.Objects;

public class RatingId implements Serializable {

    private int idUser;
    private int idAlbum;

    public RatingId() {}

    public RatingId(int idUser, int idAlbum) {
        this.idUser = idUser;
        this.idAlbum = idAlbum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RatingId)) return false;
        RatingId that = (RatingId) o;
        return idUser == that.idUser && idAlbum == that.idAlbum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idAlbum);
    }
}
