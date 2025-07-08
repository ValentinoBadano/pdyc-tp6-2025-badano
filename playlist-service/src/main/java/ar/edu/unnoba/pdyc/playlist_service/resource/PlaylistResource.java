package ar.edu.unnoba.pdyc.playlist_service.resource;

import ar.edu.unnoba.pdyc.playlist_service.DTO.PlaylistDTO;
import ar.edu.unnoba.pdyc.playlist_service.DTO.PlaylistRequestDTO;
import ar.edu.unnoba.pdyc.playlist_service.DTO.PlaylistSongRequestDTO;
import ar.edu.unnoba.pdyc.playlist_service.DTO.PlaylistSummaryDTO;
import ar.edu.unnoba.pdyc.playlist_service.service.PlaylistService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistResource {
    private PlaylistService playlistService;

    @Autowired
    public PlaylistResource(PlaylistService service){
        this.playlistService = service;
    }

    @PostMapping()
    public ResponseEntity<Long> createPlaylist(@RequestBody PlaylistRequestDTO dto){
        Long id = playlistService.createPlaylist(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PostMapping("/song")
    public ResponseEntity<Void> addSongToPlaylist(@RequestBody PlaylistSongRequestDTO dto){
        playlistService.addSongToPlaylist(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistDTO> getPlaylist(@PathVariable Long id) {
        PlaylistDTO dto = new PlaylistDTO();

        try {
            dto = playlistService.getPlaylistById(id);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<PlaylistSummaryDTO>> getAllPlaylists() {
        return ResponseEntity.ok(playlistService.getAllPlaylists());
    }

}
