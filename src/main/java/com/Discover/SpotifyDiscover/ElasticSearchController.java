package com.Discover.SpotifyDiscover;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ElasticSearchController {

    private final TokenController tokenController;
    private final ElasticSearchQuery elasticSearchQuery;

    @Autowired
    public ElasticSearchController(TokenController tokenController, ElasticSearchQuery elasticSearchQuery) {
        this.tokenController = tokenController;
        this.elasticSearchQuery = elasticSearchQuery;
    }

    @PostMapping("/createOrUpdateDocument")
    public ResponseEntity<Object> createOrUpdateDocument(@RequestBody Track track) throws IOException {
        String response = elasticSearchQuery.createOrUpdateDocument(track);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getDocument")
    public ResponseEntity<Object> getDocumentById(@RequestParam String id) throws IOException {
        Track track = elasticSearchQuery.getDocumentById(id);
        return new ResponseEntity<>(track, HttpStatus.OK);
    }

    @GetMapping("/searchDocument")
    public ResponseEntity<Object> searchAllDocument() throws IOException {
        List<Track> tracks = elasticSearchQuery.searchAllDocuments();
        return new ResponseEntity<>(tracks, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/filteredTracks")
    public ResponseEntity<?> getFilteredTracks(@RequestHeader("Authorization") String authHeader) {
        try {
            // Primero, obtenemos la pista media.
            ResponseEntity<?> meanTrackResponse = this.tokenController.getAudioFeatures(authHeader);
            if (!meanTrackResponse.getStatusCode().is2xxSuccessful() || !(meanTrackResponse.getBody() instanceof Track)) {
                // Si no pudimos obtener la pista media, devolvemos un error al cliente.
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get mean track");
            }

            Track meanTrack = (Track) meanTrackResponse.getBody();

            // Usamos los valores de la pista media para realizar una búsqueda filtrada.
            List<Track> filteredTracks = this.elasticSearchQuery.searchFilteredDocuments(
                    meanTrack.getDanceability(),
                    meanTrack.getEnergy(),
                    meanTrack.getLoudness(),
                    meanTrack.getSpeechiness(),
                    meanTrack.getAcousticness(),
                    meanTrack.getInstrumentalness(),
                    meanTrack.getLiveness(),
                    meanTrack.getValence(),
                    meanTrack.getTempo(),
                    0.5  // Supongamos que queremos una tolerancia del 10%
            );

            if (filteredTracks.size() > 20) {
                filteredTracks = filteredTracks.subList(0, 20);
            }
            //just the id
            List<String> filteredTracksIds = filteredTracks.stream().map(Track::getId).collect(Collectors.toList());
            return ResponseEntity.ok(filteredTracksIds);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

}