package models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"name","height","mass","hair_color","skin_color","eye_color","birth_year","gender","homeworld","films","species","vehicles","starships","created","edited","url"})
public class PeopleModel {

    private String _name;
    private String _height;
    private String _mass;
    @JsonProperty("hair_color")
    private String _hair_color;
    @JsonProperty("skin_color")
    private String _skin_color;
    @JsonProperty("eye_color")
    private String _eye_color;
    @JsonProperty("birth_year")
    private String _birth_year;
    private String _gender;
    private String _homeworld;
    private String[] _films;
    private String[] _species;
    private String[] _vehicles;
    private String[] _starships;
    private String _created;
    private String _edited;
    private String _url;

    public PeopleModel() {
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getHeight() {
        return _height;
    }

    public void setHeight(String height) {
        _height = height;
    }

    public String getMass() {
        return _mass;
    }

    public void setMass(String mass) {
        _mass = mass;
    }

    public String getHairColor() {
        return _hair_color;
    }

    public void setHairColor(String hairColor) {
        _hair_color = hairColor;
    }

    public String getSkinColor() {
        return _skin_color;
    }

    public void setSkinColor(String skinColor) {
        _skin_color = skinColor;
    }

    public String getEyeColor() {
        return _eye_color;
    }

    public void setEyeColor(String eyeColor) {
        _eye_color = eyeColor;
    }

    public String getBirthYear() {
        return _birth_year;
    }

    public void setBirthYear(String birthYear) {
        _birth_year = birthYear;
    }

    public String getGender() {
        return _gender;
    }

    public void setGender(String gender) {
        _gender = gender;
    }

    public String getHomeworld() {
        return _homeworld;
    }

    public void setHomeworld(String homeworld) {
        _homeworld = homeworld;
    }

    public String[] getFilms() {
        return _films;
    }

    public void setFilms(String[] films) {
        _films = films;
    }

    public String[] getSpecies() {
        return _species;
    }

    public void setSpecies(String[] species) {
        _species = species;
    }

    public String[] getVehicles() {
        return _vehicles;
    }

    public void setVehicles(String[] vehicles) {
        _vehicles = vehicles;
    }

    public String[] getStarships() {
        return _starships;
    }

    public void setStarships(String[] starships) {
        _starships = starships;
    }

    public String getCreated() {
        return _created;
    }

    public void setCreated(String created) {
        _created = created;
    }

    public String getEdited() {
        return _edited;
    }

    public void setEdited(String edited) {
        _edited = edited;
    }

    public String getUrl() {
        return _url;
    }

    public void setUrl(String url) {
        _url = url;
    }

}
