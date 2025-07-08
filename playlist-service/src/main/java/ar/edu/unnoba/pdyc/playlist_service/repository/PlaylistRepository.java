package ar.edu.unnoba.pdyc.playlist_service.repository;

import ar.edu.unnoba.pdyc.playlist_service.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
