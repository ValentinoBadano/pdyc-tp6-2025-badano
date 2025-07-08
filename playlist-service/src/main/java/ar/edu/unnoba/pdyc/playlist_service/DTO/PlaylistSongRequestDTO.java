package ar.edu.unnoba.pdyc.playlist_service.DTO;

public class PlaylistSongRequestDTO {
    private Long playlistId;
    private Long songId;

    public Long getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(Long playlistId) {
        this.playlistId = playlistId;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }
}
