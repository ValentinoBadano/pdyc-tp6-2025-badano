package ar.edu.unnoba.pdyc.song_service.repository;

import ar.edu.unnoba.pdyc.song_service.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
}
