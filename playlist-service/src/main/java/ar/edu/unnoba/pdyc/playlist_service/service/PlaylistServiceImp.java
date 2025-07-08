package ar.edu.unnoba.pdyc.playlist_service.service;

import ar.edu.unnoba.pdyc.playlist_service.DTO.*;
import ar.edu.unnoba.pdyc.playlist_service.model.Playlist;
import ar.edu.unnoba.pdyc.playlist_service.model.PlaylistSong;
import ar.edu.unnoba.pdyc.playlist_service.repository.PlaylistRepository;
import ar.edu.unnoba.pdyc.playlist_service.repository.PlaylistSongRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistServiceImp implements PlaylistService{

    private final PlaylistRepository playlistRepository;
    private final PlaylistSongRepository songRepository;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    @Autowired
    public PlaylistServiceImp(PlaylistRepository playlist, PlaylistSongRepository song, DiscoveryClient discovery, RestClient.Builder restClientBuilder){
        this.playlistRepository = playlist;
        this.songRepository = song;
        this.discoveryClient = discovery;
        this.restClient = restClientBuilder.build();
    }



    @Override
    public PlaylistDTO getPlaylistById(Long id) {
        Playlist playlist = playlistRepository.findById(id)
                                              .orElseThrow(() -> new EntityNotFoundException("Playlist no hallada."));
        PlaylistDTO dto = PlaylistMapper.toDTO(playlist);

        List<PlaylistSong> playlistSongsIds = songRepository.findByPlaylistId(id);
        List<SongDTO> songs = new ArrayList<>();
        for (PlaylistSong songId : playlistSongsIds) {
            SongDTO songDTO = getSongFromSongService(songId.getSongId());
            songs.add(songDTO);
        }

        dto.setSongs(songs);

        return dto;
    }

    @Override
    public SongDTO getSongFromSongService(Long songId){
        // Descubrir song-service en Eureka
        List<ServiceInstance> instances = discoveryClient.getInstances("song-service");
        if (instances.isEmpty()) {
            throw new RuntimeException("No se hallaron instancias de song-service");
        }

        String baseUrl = instances.get(0).getUri().toString();

        // Llamada a "/song/{id}" para obtener la cancion.
        return restClient.get().uri(baseUrl + "/songs/" + songId).retrieve().body(SongDTO.class);
    }

    @Override
    public List<PlaylistSummaryDTO> getAllPlaylists() {
        List<Playlist> playlists = playlistRepository.findAll();
        List<PlaylistSummaryDTO> dtos = new ArrayList<>();

        for (Playlist playlist : playlists) {
            List<PlaylistSong> songs = songRepository.findByPlaylistId(playlist.getId());
            PlaylistSummaryDTO dto = new PlaylistSummaryDTO();

            dto.setId(playlist.getId());
            dto.setName(playlist.getName());
            dto.setSongCount(songs.size());

            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    public Long createPlaylist(PlaylistRequestDTO dto) { //Se crea playlist sin canciones
        Playlist playlist = new Playlist();
        playlist.setName(dto.getName());
        playlistRepository.save(playlist);
        return playlist.getId();
    }

    @Override
    public void addSongToPlaylist(PlaylistSongRequestDTO dto) {
        playlistRepository.findById(dto.getPlaylistId()) //valida si la playlist existe.
                          .orElseThrow(() -> new EntityNotFoundException("Playlist no hallada."));

        PlaylistSong song = new PlaylistSong();
        song.setPlaylistId(dto.getPlaylistId());
        song.setSongId(dto.getSongId());
        songRepository.save(song);
    }
}
