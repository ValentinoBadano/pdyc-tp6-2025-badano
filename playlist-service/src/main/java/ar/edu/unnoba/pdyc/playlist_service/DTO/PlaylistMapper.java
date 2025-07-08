package ar.edu.unnoba.pdyc.playlist_service.DTO;

import ar.edu.unnoba.pdyc.playlist_service.model.Playlist;

public class PlaylistMapper {
    public static PlaylistDTO toDTO(Playlist playlist){
        PlaylistDTO dto = new PlaylistDTO();
        dto.setId(playlist.getId());
        dto.setName(playlist.getName());
        return dto;
    }
}
