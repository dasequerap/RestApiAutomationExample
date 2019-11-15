package models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;

import java.net.URL;

@CsvDataType
@JsonIgnoreProperties(ignoreUnknown = true)
public class PeopleModel {

    @CsvField(pos = 1)
    private String _name;
    @CsvField(pos = 2)
    private String _height;
    @CsvField(pos = 3)
    private String _mass;
    @JsonProperty("hair_color")
    private String _hairColor;
    @JsonProperty("skin_color")
    private String _skinColor;
    @JsonProperty("eye_color")
    private String _eyeColor;
    @JsonProperty("birth_year")
    private String _birthYear;
    private String _gender;
    private URL _homeworld;
    private URL[] _films;
    private URL[] _species;
    private URL[] _vehicles;
    private URL[] _starships;
    private String _created;
    private String _edited;
    private URL _url;

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
        return _hairColor;
    }

    public void setHairColor(String hairColor) {
        _hairColor = hairColor;
    }

    public String getSkinColor() {
        return _skinColor;
    }

    public void setSkinColor(String skinColor) {
        _skinColor = skinColor;
    }

    public String getEyeColor() {
        return _eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        _eyeColor = eyeColor;
    }

    public String getBirthYear() {
        return _birthYear;
    }

    public void setBirthYear(String birthYear) {
        _birthYear = birthYear;
    }

    public String getGender() {
        return _gender;
    }

    public void setGender(String gender) {
        _gender = gender;
    }

    public URL getHomeworld() {
        return _homeworld;
    }

    public void setHomeworld(URL homeworld) {
        _homeworld = homeworld;
    }

    public URL[] getFilms() {
        return _films;
    }

    public void setFilms(URL[] films) {
        _films = films;
    }

    public URL[] getSpecies() {
        return _species;
    }

    public void setSpecies(URL[] species) {
        _species = species;
    }

    public URL[] getVehicles() {
        return _vehicles;
    }

    public void setVehicles(URL[] vehicles) {
        _vehicles = vehicles;
    }

    public URL[] getStarships() {
        return _starships;
    }

    public void setStarships(URL[] starships) {
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

    public URL getUrl() {
        return _url;
    }

    public void setUrl(URL url) {
        _url = url;
    }

}
