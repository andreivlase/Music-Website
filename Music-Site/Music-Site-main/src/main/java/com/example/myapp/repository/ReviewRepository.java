package com.example.myapp.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.myapp.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByIdUser(int idUser);
    List<Review> findByIdAlbum(int idAlbum);
    @Query("SELECT COALESCE(AVG(r.rating), 0) FROM Rating r WHERE r.idAlbum = :idAlbum")
    double calculateAverageRating(@Param("idAlbum") int idAlbum);
}
