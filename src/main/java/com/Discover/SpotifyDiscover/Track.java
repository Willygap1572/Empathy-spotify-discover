package com.Discover.SpotifyDiscover;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "tracks")
public class Track {

    @Id
    @Field(type = FieldType.Text, name = "id")
    private String id;

    @Field(type = FieldType.Integer, name = "column1")
    private Integer column1;

    @Field(type = FieldType.Integer, name = "duration_ms")
    private int duration_ms;

    @Field(type = FieldType.Boolean, name = "explicit")
    private Boolean explicit;

    @Field(type = FieldType.Integer, name = "key")
    private int key;

    @Field(type = FieldType.Double, name = "loudness")
    private Double loudness;

    @Field(type = FieldType.Integer, name = "popularity")
    private int popularity;

    @Field(type = FieldType.Double, name = "danceability")
    private Double danceability;

    @Field(type = FieldType.Double, name = "energy")
    private Double energy;

    @Field(type = FieldType.Integer, name = "mode")
    private int mode;

    @Field(type = FieldType.Double, name = "speechiness")
    private Double speechiness;

    @Field(type = FieldType.Double, name = "acousticness")
    private Double acousticness;

    @Field(type = FieldType.Double, name = "instrumentalness")
    private Double instrumentalness;

    @Field(type = FieldType.Double, name = "liveness")
    private Double liveness;

    @Field(type = FieldType.Double, name = "valence")
    private Double valence;

    @Field(type = FieldType.Double, name = "tempo")
    private Double tempo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getColumn1() {
        return column1;
    }

    public void setColumn1(Integer column1) {
        this.column1 = column1;
    }

    public int getDuration_ms() {
        return duration_ms;
    }

    public void setDuration_ms(int duration_ms) {
        this.duration_ms = duration_ms;
    }

    public Boolean getExplicit() {
        return explicit;
    }

    public void setExplicit(Boolean explicit) {
        this.explicit = explicit;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Double getLoudness() {
        return loudness;
    }

    public void setLoudness(Double loudness) {
        this.loudness = loudness;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public Double getDanceability() {
        return danceability;
    }

    public void setDanceability(Double danceability) {
        this.danceability = danceability;
    }

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public Double getSpeechiness() {
        return speechiness;
    }

    public void setSpeechiness(Double speechiness) {
        this.speechiness = speechiness;
    }

    public Double getAcousticness() {
        return acousticness;
    }

    public void setAcousticness(Double acousticness) {
        this.acousticness = acousticness;
    }

    public Double getInstrumentalness() {
        return instrumentalness;
    }

    public void setInstrumentalness(Double instrumentalness) {
        this.instrumentalness = instrumentalness;
    }

    public Double getLiveness() {
        return liveness;
    }

    public void setLiveness(Double liveness) {
        this.liveness = liveness;
    }

    public Double getValence() {
        return valence;
    }

    public void setValence(Double valence) {
        this.valence = valence;
    }

    public Double getTempo() {
        return tempo;
    }

    public void setTempo(Double tempo) {
        this.tempo = tempo;
    }
}

