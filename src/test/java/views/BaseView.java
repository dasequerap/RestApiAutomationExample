package views;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class BaseView {

    String _currentURI;
    private String _baseURI;
    private String _resource;
    ValidatableResponse _apiResponse;
    private RequestSpecification _request;

    BaseView() { }

    String getBaseURI() { return this._baseURI; }

    private String getResource() { return this._resource; }

    RequestSpecification getRequest(){ return this._request; }

    public ValidatableResponse getHead(){
        return given().head(_currentURI).then();
    }

    public ValidatableResponse getOptions(){
        return given().options(_currentURI).then();
    }

    void setBaseURI(String baseURI) { this._baseURI = baseURI; }

    void setResource(String resource) {this._resource = resource; }

    void setRequest(RequestSpecification request){ this._request = request; }

    public ValidatableResponse post() {
        return _request.body("").post(this.getResource()).then().log().all();
    }

}
