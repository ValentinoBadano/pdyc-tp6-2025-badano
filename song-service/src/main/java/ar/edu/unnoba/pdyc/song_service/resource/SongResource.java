package ar.edu.unnoba.pdyc.song_service.resource;

import ar.edu.unnoba.pdyc.song_service.DTO.SongDTO;
import ar.edu.unnoba.pdyc.song_service.DTO.SongRequestDTO;
import ar.edu.unnoba.pdyc.song_service.service.SongServiceImp;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongResource {
    private final SongServiceImp songService;

    @Autowired
    public SongResource(SongServiceImp service){
        this.songService = service;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody SongRequestDTO dto){
        songService.createSong(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongDTO> getById(@PathVariable Long id){
        SongDTO dto;
        try {
            dto = songService.findById(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<SongDTO>> getAll(){
        return ResponseEntity.ok(songService.getSongs());
    }
}
