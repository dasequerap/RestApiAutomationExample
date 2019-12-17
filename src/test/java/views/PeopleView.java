package views;

import helpers.config.MappingReader;
import java.io.IOException;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class PeopleView extends BaseView{

    private String _nextUrl = null;
    private String _previousUrl = null;
    private int _currentPage = 1;

    public PeopleView() throws IOException {
        MappingReader _peopleMapping = new MappingReader();
        _currentURI = _peopleMapping.getFullResourceURI(MappingReader.Resources.PEOPLE);
        this.setBaseURI(_peopleMapping.getServiceURI());
        RestAssured.baseURI = this.getBaseURI();
        this.setResource(_peopleMapping.getResource(MappingReader.Resources.PEOPLE));
        this.setRequest(RestAssured.given());
    }

    private void setCurrentPage(int currentPage) {
        _currentPage = currentPage;
    }

    private String getNextUrl() {
        return _nextUrl;
    }

    private String getPreviousUrl() {
        return _previousUrl;
    }

    public ValidatableResponse getPeople(int page) {
        _currentPage = page;

        if (_currentPage > 1) {
            this.setCurrentPage(page);
        }
        _apiResponse = this.getRequest().queryParam("page", page).when()
                .get( _currentURI ).then();
        _nextUrl = _apiResponse.extract().path("next");
        _previousUrl = _apiResponse.extract().path("previous");
        return _apiResponse;
    }

    public ValidatableResponse getPeopleById(int peopleId) {
        return this.getRequest().log().everything().when()
                .get( _currentURI + "{peopleId}", peopleId).then().log().everything();
    }

    public ValidatableResponse goToNextURL(){
        _apiResponse = this.getRequest().when().get(this.getNextUrl()).then();
        return _apiResponse;
    }

    public ValidatableResponse goToPreviousURL(){
        _apiResponse = this.getRequest().when().get(this.getPreviousUrl()).then();
        return _apiResponse;
    }

    public String getResults(){
        return this.getPeople(this._currentPage).extract().path("results").toString();
    }
}
