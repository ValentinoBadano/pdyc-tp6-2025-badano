package ar.edu.unnoba.pdyc.playlist_service.DTO;

import java.util.List;

public class PlaylistDTO {
    private Long id;
    private String name;
    private List<SongDTO> songs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SongDTO> getSongs() {
        return songs;
    }

    public void setSongs(List<SongDTO> songs) {
        this.songs = songs;
    }
}
