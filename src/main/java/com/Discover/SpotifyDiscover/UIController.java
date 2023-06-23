package com.Discover.SpotifyDiscover;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class UIController {

    @Autowired
    private ElasticSearchQuery elasticSearchQuery;

    @GetMapping("/")
    public String viewHomePage(Model model) throws IOException {
        model.addAttribute("listTrackDocuments",elasticSearchQuery.searchAllDocuments());
        return "index";
    }

    @PostMapping("/saveTrack")
    public String saveTrack(@ModelAttribute("track") Track track) throws IOException {
        System.out.println("Track name: " + track.getTrack_name());
        elasticSearchQuery.createOrUpdateDocument(track);
        return "redirect:/";
    }

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