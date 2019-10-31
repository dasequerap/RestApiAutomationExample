package views;

import helpers.StarWarsApiURIs;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class PeopleView {

    private String _nextUrl = null;
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

    public ValidatableResponse getPeople(int page) {
        _currentPage = page;

        if (_currentPage > 1) {
            _peopleURI += "?page=" + page;
            this.setCurrentPage(page);
        }

        _apiResponse = given().when().get(_peopleURI).then();
        _nextUrl = _apiResponse.extract().path("next");
        _peopleURI = new StarWarsApiURIs().peopleURI;
        return _apiResponse;
    }

    public ValidatableResponse getPeopleById(int peopleId) {
        return given().log().everything().when()
                .get(_peopleURI + peopleId + "/").then().log().everything();
    }
}
