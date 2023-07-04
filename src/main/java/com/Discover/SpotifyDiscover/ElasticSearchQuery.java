package com.Discover.SpotifyDiscover;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ElasticSearchQuery {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    private final String indexName = "tracks";


    public String createOrUpdateDocument(Track track) throws IOException {

        IndexResponse response = elasticsearchClient.index(i -> i
                .index(indexName)
                .id(track.getId())
                .document(track)
        );
        System.out.println("Response: " + track.getName());
        if(response.result().name().equals("Created")){
            return new StringBuilder("Document has been successfully created.").toString();
        }else if(response.result().name().equals("Updated")){
            return new StringBuilder("Document has been successfully updated.").toString();
        }
        return new StringBuilder("Error while performing the operation.").toString();
    }

    public Track getDocumentById(String id) throws IOException{
        Track track = null;
        GetResponse<Track> response = elasticsearchClient.get(g -> g
                        .index(indexName)
                        .id(id),
                Track.class
        );

        if (response.found()) {
            track = response.source();
            System.out.println("Track name " + track.getName());
        } else {
            System.out.println ("Track not found");
        }

        return track;
    }

    public String deleteDocumentById(String id) throws IOException {

        DeleteRequest request = DeleteRequest.of(d -> d.index(indexName).id(id));

        DeleteResponse deleteResponse = elasticsearchClient.delete(request);
        if (Objects.nonNull(deleteResponse.result()) && !deleteResponse.result().name().equals("NotFound")) {
            return new StringBuilder("Track with id " + deleteResponse.id() + " has been deleted.").toString();
        }
        System.out.println("Track not found");
        return new StringBuilder("Track with id " + deleteResponse.id()+" does not exist.").toString();

    }

    public  List<Track> searchAllDocuments() throws IOException {

        SearchRequest searchRequest =  SearchRequest.of(s -> s.index(indexName));
        SearchResponse searchResponse =  elasticsearchClient.search(searchRequest, Track.class);
        List<Hit> hits = searchResponse.hits().hits();
        List<Track> tracks = new ArrayList<>();
        for(Hit object : hits){

            System.out.print(((Track) object.source()));
            tracks.add((Track) object.source());

        }
        return tracks;
    }
}