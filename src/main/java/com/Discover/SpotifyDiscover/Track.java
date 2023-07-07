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

    /*same with 'album', 'artists', 'duration_ms', 'explicit',
       'name', 'popularity', 'danceability', 'energy', 'key', 'loudness',
       'mode', 'speechiness', 'acousticness', 'instrumentalness', 'liveness',
       'valence', 'tempo*/

    @Field(type = FieldType.Text, name = "album")
    private String album;

    @Field(type = FieldType.Text, name = "artists")
    private String artists;

    /*column1*/
    @Field(type = FieldType.Integer, name = "column1")
    private Integer column1;

    @Field(type = FieldType.Double, name = "duration_ms")
    private Double duration_ms;

    @Field(type = FieldType.Boolean, name = "explicit")
    private Boolean explicit;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Text, name = "key")
    private String key;

    @Field(type = FieldType.Double, name = "loudness")
    private Double loudness;

    @Field(type = FieldType.Text, name = "popularity")
    private String popularity;

    @Field(type = FieldType.Double, name = "danceability")
    private Double danceability;

    @Field(type = FieldType.Double, name = "energy")
    private Double energy;

    @Field(type = FieldType.Text, name = "mode")
    private String mode;

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

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public Double getDuration_ms() {
        return duration_ms;
    }

    public void setDuration_ms(Double duration_ms) {
        this.duration_ms = duration_ms;
    }

    public Boolean getExplicit() {
        return explicit;
    }

    public void setExplicit(Boolean explicit) {
        this.explicit = explicit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Double getLoudness() {
        return loudness;
    }

    public void setLoudness(Double loudness) {
        this.loudness = loudness;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
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

