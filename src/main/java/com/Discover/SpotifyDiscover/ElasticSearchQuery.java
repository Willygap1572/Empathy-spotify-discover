package com.Discover.SpotifyDiscover;

import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import org.springframework.beans.factory.annotation.Autowired;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import co.elastic.clients.elasticsearch.core.search.Hit;
import org.springframework.stereotype.Repository;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.json.JsonData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

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
        if (response.result().name().equals("Created")) {
            return new StringBuilder("Document has been successfully created.").toString();
        } else if (response.result().name().equals("Updated")) {
            return new StringBuilder("Document has been successfully updated.").toString();
        }
        return new StringBuilder("Error while performing the operation.").toString();
    }

    public Track getDocumentById(String id) throws IOException {
        Track track = null;
        GetResponse<Track> response = elasticsearchClient.get(g -> g
                        .index(indexName)
                        .id(id),
                Track.class
        );

        if (response.found()) {
            track = response.source();
            System.out.println("Track id " + track.getId());
        } else {
            System.out.println("Track not found");
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
        return new StringBuilder("Track with id " + deleteResponse.id() + " does not exist.").toString();

    }

    public List<Track> searchAllDocuments() throws IOException {

        SearchRequest searchRequest = SearchRequest.of(s -> s.index(indexName));
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, Track.class);
        List<Hit> hits = searchResponse.hits().hits();
        List<Track> tracks = new ArrayList<>();
        for (Hit object : hits) {

            tracks.add((Track) object.source());

        }
        return tracks;
    }

    public List<Track> searchFilteredDocuments(double danceability, double energy, double loudness, double speechiness, double acousticness, double instrumentalness, double liveness, double valence, double tempo, double tolerance) throws IOException {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        Query byDanceability = RangeQuery.of(r -> r
                        .field("danceability")
                        .gte(JsonData.of(danceability - tolerance))
                .lte(JsonData.of(danceability + tolerance))
        )._toQuery();

        Query byEnergy = RangeQuery.of(r -> r
                        .field("energy")
                        .gte(JsonData.of(energy - tolerance))
                .lte(JsonData.of(energy + tolerance))
        )._toQuery();

        Query byLoudness = RangeQuery.of(r -> r
                        .field("loudness")
                        .gte(JsonData.of(loudness - tolerance))
                .lte(JsonData.of(loudness + tolerance))
        )._toQuery();

        Query bySpeechiness = RangeQuery.of(r -> r
                        .field("speechiness")
                        .gte(JsonData.of(speechiness - tolerance))
                .lte(JsonData.of(speechiness + tolerance))
        )._toQuery();

        Query byAcousticness = RangeQuery.of(r -> r
                        .field("acousticness")
                        .gte(JsonData.of(acousticness - tolerance))
                .lte(JsonData.of(acousticness + tolerance))
        )._toQuery();

        Query byInstrumentalness = RangeQuery.of(r -> r
                        .field("instrumentalness")
                        .gte(JsonData.of(instrumentalness - tolerance))
                .lte(JsonData.of(instrumentalness + tolerance))
        )._toQuery();

        Query byLiveness = RangeQuery.of(r -> r
                        .field("liveness")
                        .gte(JsonData.of(liveness - tolerance))
                .lte(JsonData.of(liveness + tolerance))
        )._toQuery();

        Query byValence = RangeQuery.of(r -> r
                        .field("valence")
                        .gte(JsonData.of(valence - tolerance))
                .lte(JsonData.of(valence + tolerance))
        )._toQuery();

        Query byTempo = RangeQuery.of(r -> r
                        .field("tempo")
                        .gte(JsonData.of(tempo - tolerance))
                .lte(JsonData.of(tempo + tolerance))
        )._toQuery();

        SearchRequest searchRequest = SearchRequest.of(s -> s.index(indexName)
                .query(q -> q
                        .bool(b -> b
                                .must(byDanceability)
                                .must(byEnergy)
                                .must(byLoudness)
                                .must(bySpeechiness)
                                .must(byAcousticness)
                                .must(byInstrumentalness)
                                .must(byLiveness)
                                .must(byValence)
                                .must(byTempo)
                        ))
        );
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, Track.class);
        List<Hit> hits = searchResponse.hits().hits();
        List<Track> tracks = new ArrayList<>();
        for (Hit object : hits) {
            tracks.add((Track) object.source());
        }
        return tracks;
    }

    public String getIndexName() {
        return indexName;
    }
}