package ar.edu.unnoba.pdyc.song_service.service;

import ar.edu.unnoba.pdyc.song_service.DTO.SongDTO;
import ar.edu.unnoba.pdyc.song_service.DTO.SongMapper;
import ar.edu.unnoba.pdyc.song_service.DTO.SongRequestDTO;
import ar.edu.unnoba.pdyc.song_service.model.Song;
import ar.edu.unnoba.pdyc.song_service.repository.SongRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImp implements SongService {

    private final SongRepository songRepository;

    @Autowired
    public SongServiceImp(SongRepository repository){
        this.songRepository = repository;
    }

    @Override
    public void createSong(SongRequestDTO dto) {
        songRepository.save(SongMapper.fromRequest(dto));
    }

    @Override
    public SongDTO findById(Long id) {
        Song song = songRepository.findById(id)
                                  .orElseThrow(() -> new EntityNotFoundException("Canción no hallada."));


        return SongMapper.toDTO(song);
    }

    @Override
    public List<SongDTO> getSongs() {
        List<Song> songs = songRepository.findAll();
        return songs.stream().map(SongMapper::toDTO).toList();
    }

    public SongRepository getSongRepository() {
        return songRepository;
    }

}
