package ar.edu.unnoba.pdyc.song_service.service;

import ar.edu.unnoba.pdyc.song_service.DTO.SongDTO;
import ar.edu.unnoba.pdyc.song_service.DTO.SongRequestDTO;

import java.util.List;

public interface SongService {
    public List<SongDTO> getSongs();
    public SongDTO findById(Long id);
    public void createSong(SongRequestDTO dto);
}
