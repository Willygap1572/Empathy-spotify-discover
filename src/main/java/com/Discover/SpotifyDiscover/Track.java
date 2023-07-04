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

    @Field(type = FieldType.Float, name = "duration_ms")
    private Float duration_ms;

    @Field(type = FieldType.Boolean, name = "explicit")
    private Boolean explicit;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Text, name = "key")
    private String key;

    @Field(type = FieldType.Float, name = "loudness")
    private Float loudness;

    @Field(type = FieldType.Text, name = "popularity")
    private String popularity;

    @Field(type = FieldType.Float, name = "danceability")
    private Float danceability;

    @Field(type = FieldType.Text, name = "energy")
    private String energy;

    @Field(type = FieldType.Text, name = "mode")
    private String mode;

    @Field(type = FieldType.Float, name = "speechiness")
    private Float speechiness;

    @Field(type = FieldType.Float, name = "acousticness")
    private Float acousticness;

    @Field(type = FieldType.Float, name = "instrumentalness")
    private Float instrumentalness;

    @Field(type = FieldType.Float, name = "liveness")
    private Float liveness;

    @Field(type = FieldType.Float, name = "valence")
    private Float valence;

    @Field(type = FieldType.Float, name = "tempo")
    private Float tempo;

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

    public Float getDuration_ms() {
        return duration_ms;
    }

    public void setDuration_ms(Float duration_ms) {
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

    public Float getLoudness() {
        return loudness;
    }

    public void setLoudness(Float loudness) {
        this.loudness = loudness;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public Float getDanceability() {
        return danceability;
    }

    public void setDanceability(Float danceability) {
        this.danceability = danceability;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Float getSpeechiness() {
        return speechiness;
    }

    public void setSpeechiness(Float speechiness) {
        this.speechiness = speechiness;
    }

    public Float getAcousticness() {
        return acousticness;
    }

    public void setAcousticness(Float acousticness) {
        this.acousticness = acousticness;
    }

    public Float getInstrumentalness() {
        return instrumentalness;
    }

    public void setInstrumentalness(Float instrumentalness) {
        this.instrumentalness = instrumentalness;
    }

    public Float getLiveness() {
        return liveness;
    }

    public void setLiveness(Float liveness) {
        this.liveness = liveness;
    }

    public Float getValence() {
        return valence;
    }

    public void setValence(Float valence) {
        this.valence = valence;
    }

    public Float getTempo() {
        return tempo;
    }

    public void setTempo(Float tempo) {
        this.tempo = tempo;
    }
}

