package com.Discover.SpotifyDiscover;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ElasticSearchController {

    @Autowired
    private ElasticSearchQuery elasticSearchQuery;

    @PostMapping("/createOrUpdateDocument")
    public ResponseEntity<Object> createOrUpdateDocument(@RequestBody Track track) throws IOException {
        String response = elasticSearchQuery.createOrUpdateDocument(track);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getDocument")
    public ResponseEntity<Object> getDocumentById(@RequestParam String id) throws IOException {
        Track track =  elasticSearchQuery.getDocumentById(id);
        return new ResponseEntity<>(track, HttpStatus.OK);
    }

    @DeleteMapping("/deleteDocument")
    public ResponseEntity<Object> deleteDocumentById(@RequestParam String id) throws IOException {
        String response =  elasticSearchQuery.deleteDocumentById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/searchDocument")
    public ResponseEntity<Object> searchAllDocument() throws IOException {
        List<Track> tracks = elasticSearchQuery.searchAllDocuments();
        return new ResponseEntity<>(tracks, HttpStatus.OK);
    }
}