package ar.edu.unnoba.pdyc.song_service.DTO;

import ar.edu.unnoba.pdyc.song_service.model.Song;

public class SongMapper {

    public static SongDTO toDTO(Song song){
        SongDTO dto = new SongDTO();
        dto.setId(song.getId());
        dto.setName(song.getName());
        return dto;
    }

    public static Song fromRequest(SongRequestDTO dto){
        Song song = new Song();
        song.setName(dto.getName());
        return song;
    }

}
