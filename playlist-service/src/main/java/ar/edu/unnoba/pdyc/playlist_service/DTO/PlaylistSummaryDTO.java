package ar.edu.unnoba.pdyc.playlist_service.DTO;

public class PlaylistSummaryDTO {
    private Long id;
    private String name;
    private Integer songCount;

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

    public Integer getSongCount() {
        return songCount;
    }

    public void setSongCount(Integer songCount) {
        this.songCount = songCount;
    }
}
