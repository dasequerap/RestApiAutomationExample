package views;

import helpers.StarWarsApiURIs;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;

public class PeopleView {

    private String _nextUrl = null;
    private String _previousUrl = null;
    private String _peopleURI;
    private int _currentPage = 1;
    private ValidatableResponse _apiResponse;

    public PeopleView() {
        _peopleURI = new StarWarsApiURIs().peopleURI;
    }

    public int getCurrentPage() {
        return _currentPage;
    }

    private void setCurrentPage(int nextPage) {
        _currentPage = nextPage;
    }

    public String getNextUrl() {
        return _nextUrl;
    }

    public String getPreviousUrl() {
        return _previousUrl;
    }

    public ValidatableResponse getPeople(int page) {
        _currentPage = page;

        if (_currentPage > 1) {
            this.setCurrentPage(page);
        }
        _apiResponse = given().queryParam("page", page).when().get(_peopleURI)
                .then();
        _nextUrl = _apiResponse.extract().path("next");
        _previousUrl = _apiResponse.extract().path("previous");
        _peopleURI = new StarWarsApiURIs().peopleURI;
        return _apiResponse;
    }

    public ValidatableResponse getPeopleById(int peopleId) {
        return given().log().everything().when()
                .get(_peopleURI + "{peopleId}", peopleId).then().log().everything();
    }

    public ValidatableResponse goToNextURL(){
        _apiResponse = given().when().get(this.getNextUrl()).then();
        return _apiResponse;
    }

    public ValidatableResponse goToPreviousURL(){
        _apiResponse = given().when().get(this.getPreviousUrl()).then();
        return _apiResponse;
    }

    public String getResults(){
        return this.getPeople(this._currentPage).extract().path("results").toString();
    }
}
