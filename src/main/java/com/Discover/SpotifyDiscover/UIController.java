package com.Discover.SpotifyDiscover;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//Map
import java.util.Map;
import java.util.HashMap;
//responseEntity
import org.springframework.http.ResponseEntity;


import java.io.IOException;

@Controller
public class UIController {

    @Autowired
    private ElasticSearchQuery elasticSearchQuery;


    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") String id, Model model) throws IOException {

        Track track = elasticSearchQuery.getDocumentById(id);
        model.addAttribute("track", track);
        return "updateTrackDocument";
    }

    @GetMapping("/showNewTrackForm")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind form data
        Track track = new Track();
        model.addAttribute("track", track);
        return "newTrackDocument";
    }

    @GetMapping("/deleteTrack/{id}")
    public String deleteTrack(@PathVariable(value = "id") String id) throws IOException {

        this.elasticSearchQuery.deleteDocumentById(id);
        return "redirect:/";
    }


}