package ar.edu.unnoba.pdyc.playlist_service.repository;

import ar.edu.unnoba.pdyc.playlist_service.model.PlaylistSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, Long> {
    public List<PlaylistSong> findByPlaylistId(Long playlistId);
}
