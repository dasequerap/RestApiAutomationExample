package views;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class BaseView {

    protected String _currentURI;
    private String _baseURI;
    private String _resource;
    protected ValidatableResponse _apiResponse;
    private RequestSpecification _request;

    BaseView() { }

    protected String getBaseURI() { return this._baseURI; }

    protected String getResource() { return this._resource; }

    protected RequestSpecification getRequest(){ return this._request; }

    public ValidatableResponse getHead(){
        return given().head(_currentURI).then();
    }

    public ValidatableResponse getOptions(){
        return given().options(_currentURI).then();
    }

    protected void setBaseURI(String baseURI) { this._baseURI = baseURI; }

    protected void setResource(String resource) {this._resource = resource; }

    protected void setRequest(RequestSpecification request){ this._request = request; }

    public ValidatableResponse post() {
        return _request.body("").post(this.getResource()).then().log().all();
    }

}
