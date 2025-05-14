package com.example.myapp.repository;

import com.example.myapp.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    List<Album> findTop10ByOrderByRelYearDesc();
    List<Album> findTop10ByOrderByRelYearDesc(Pageable pageable);
    List<Album> findByTitleContainingIgnoreCase(String keyword);
}
