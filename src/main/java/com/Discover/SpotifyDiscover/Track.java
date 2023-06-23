package com.Discover.SpotifyDiscover;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
@Document(indexName = "tracks")
public class Track {

    @Id
    @Field(type = FieldType.Text, name = "track_id")
    private String track_id;

    @Field(type = FieldType.Text, name = "genre")
    private String genre;

    @Field(type = FieldType.Text, name = "artist_name")
    private String artist_name;

    @Field(type = FieldType.Text, name = "track_name")
    private String track_name;

    @Field(type = FieldType.Integer, name = "popularity")
    private Integer popularity;

    @Field(type = FieldType.Float, name = "acousticness")
    private Float acousticness;

    @Field(type = FieldType.Float, name = "danceability")
    private Float danceability;

    @Field(type = FieldType.Integer, name = "duration_ms")
    private Integer duration_ms;

    @Field(type = FieldType.Float, name = "energy")
    private Float energy;

    @Field(type = FieldType.Float, name = "instrumentalness")
    private Float instrumentalness;

    @Field(type = FieldType.Integer, name = "key")
    private Integer key;

    @Field(type = FieldType.Float, name = "liveness")
    private Float liveness;

    @Field(type = FieldType.Float, name = "loudness")
    private Float loudness;

    @Field(type = FieldType.Integer, name = "mode")
    private Integer mode;

    @Field(type = FieldType.Float, name = "speechiness")
    private Float speechiness;

    @Field(type = FieldType.Float, name = "tempo")
    private Float tempo;

    @Field(type = FieldType.Integer, name = "time_signature")
    private Integer time_signature;

    @Field(type = FieldType.Float, name = "valence")
    private Float valence;


    public void setTrack_id(String track_id) {
        this.track_id = track_id;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public void setTrack_name(String track_name) {
        this.track_name = track_name;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public void setAcousticness(Float acousticness) {
        this.acousticness = acousticness;
    }

    public void setDanceability(Float danceability) {
        this.danceability = danceability;
    }

    public void setDuration_ms(Integer duration_ms) {
        this.duration_ms = duration_ms;
    }

    public void setEnergy(Float energy) {
        this.energy = energy;
    }

    public void setInstrumentalness(Float instrumentalness) {
        this.instrumentalness = instrumentalness;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public void setLiveness(Float liveness) {
        this.liveness = liveness;
    }

    public void setLoudness(Float loudness) {
        this.loudness = loudness;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public void setSpeechiness(Float speechiness) {
        this.speechiness = speechiness;
    }

    public void setTempo(Float tempo) {
        this.tempo = tempo;
    }

    public void setTime_signature(Integer time_signature) {
        this.time_signature = time_signature;
    }

    public void setValence(Float valence) {
        this.valence = valence;
    }

    public String getTrack_id() {
        return track_id;
    }

    public String getGenre() {
        return genre;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public String getTrack_name() {
        return track_name;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public Float getAcousticness() {
        return acousticness;
    }

    public Float getDanceability() {
        return danceability;
    }

    public Integer getDuration_ms() {
        return duration_ms;
    }

    public Float getEnergy() {
        return energy;
    }

    public Float getInstrumentalness() {
        return instrumentalness;
    }

    public Integer getKey() {
        return key;
    }

    public Float getLiveness() {
        return liveness;
    }

    public Float getLoudness() {
        return loudness;
    }

    public Integer getMode() {
        return mode;
    }

    public Float getSpeechiness() {
        return speechiness;
    }

    public Float getTempo() {
        return tempo;
    }

    public Integer getTime_signature() {
        return time_signature;
    }

    public Float getValence() {
        return valence;
    }
}

