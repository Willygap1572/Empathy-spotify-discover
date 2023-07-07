package com.Discover.SpotifyDiscover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class TokenController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.security.oauth2.client.registration.spotify.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.spotify.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.spotify.redirect-uri}")
    private String redirectUri;

    @PostMapping("/exchange")
    public ResponseEntity<?> exchangeCodeForToken(@RequestBody Map<String, String> payload) {
        String code = payload.get("code");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("code", code);
        params.add("redirect_uri", redirectUri);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity("https://accounts.spotify.com/api/token", request, Map.class);

        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping("/top-tracks")
    public ResponseEntity<?> getTopTracks(@RequestHeader("Authorization") String authHeader) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authHeader);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        String url = "https://api.spotify.com/v1/me/top/tracks?limit=50";
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseEntity.getBody());

        List<String> trackIds = new ArrayList<>();
        JsonNode tracks = root.path("items");
        for (JsonNode node : tracks) {
            String trackId = node.path("id").asText();
            trackIds.add(trackId);
        }

        return ResponseEntity.ok(trackIds);
    }

    @CrossOrigin
    @GetMapping("/audio-features")
    public ResponseEntity<?> getAudioFeatures(@RequestHeader("Authorization") String authHeader) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<List<String>> idsResponse = (ResponseEntity<List<String>>) getTopTracks(authHeader);
        List<String> ids = idsResponse.getBody();
        String query = String.join(",", ids);

        System.out.println(query);
        headers.add("Authorization", authHeader);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        String url = "https://api.spotify.com/v1/audio-features?ids=" + query;

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseEntity.getBody());

        List<Track> tracks = new ArrayList<>();
        JsonNode features = root.path("audio_features");
        for (JsonNode node : features) {
            Track track = new Track();
            track.setId(node.path("id").asText());
            track.setName(node.path("name").asText());
            track.setDanceability(node.path("danceability").asDouble());
            track.setEnergy(node.path("energy").asDouble());
            track.setLoudness(node.path("loudness").asDouble());
            track.setSpeechiness(node.path("speechiness").asDouble());
            track.setAcousticness(node.path("acousticness").asDouble());
            track.setInstrumentalness(node.path("instrumentalness").asDouble());
            track.setLiveness(node.path("liveness").asDouble());
            track.setValence(node.path("valence").asDouble());
            track.setTempo(node.path("tempo").asDouble());
            tracks.add(track);
        }
        //calculate the mean of all tracks into a single track
        Track meanTrack = new Track();
        meanTrack.setId("mean");
        meanTrack.setName("mean");
        meanTrack.setDanceability(tracks.stream().mapToDouble(Track::getDanceability).average().getAsDouble());
        meanTrack.setEnergy(tracks.stream().mapToDouble(Track::getEnergy).average().getAsDouble());
        meanTrack.setLoudness(tracks.stream().mapToDouble(Track::getLoudness).average().getAsDouble());
        meanTrack.setSpeechiness(tracks.stream().mapToDouble(Track::getSpeechiness).average().getAsDouble());
        meanTrack.setAcousticness(tracks.stream().mapToDouble(Track::getAcousticness).average().getAsDouble());
        meanTrack.setInstrumentalness(tracks.stream().mapToDouble(Track::getInstrumentalness).average().getAsDouble());
        meanTrack.setLiveness(tracks.stream().mapToDouble(Track::getLiveness).average().getAsDouble());
        meanTrack.setValence(tracks.stream().mapToDouble(Track::getValence).average().getAsDouble());
        meanTrack.setTempo(tracks.stream().mapToDouble(Track::getTempo).average().getAsDouble());

        return ResponseEntity.ok(meanTrack);
    }
}
