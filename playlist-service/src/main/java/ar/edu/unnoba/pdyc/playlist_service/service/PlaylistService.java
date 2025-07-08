package ar.edu.unnoba.pdyc.playlist_service.service;


import ar.edu.unnoba.pdyc.playlist_service.DTO.*;

import java.util.List;

public interface PlaylistService {
    public PlaylistDTO getPlaylistById(Long id);
    public List<PlaylistSummaryDTO> getAllPlaylists();
    public Long createPlaylist(PlaylistRequestDTO dto);
    public void addSongToPlaylist(PlaylistSongRequestDTO dto);
    public SongDTO getSongFromSongService(Long songId);
}
