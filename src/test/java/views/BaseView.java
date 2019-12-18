package views;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseView {

    ValidatableResponse _apiResponse;
    String _URI;
    private String _resource;
    private RequestSpecification _request;
    private String _bodyRequest = "";

    BaseView() { }

    private String getResource() { return this._resource; }

    RequestSpecification getRequest(){ return this._request; }

    public ValidatableResponse getHead(){
        return given().head( _URI ).then();
    }

    public ValidatableResponse getOptions(){
        return given().options( _URI ).then();
    }

    void setResource(String resource) {this._resource = resource; }

    void setRequest(RequestSpecification request){ this._request = request; }

    public ValidatableResponse post() {
        return _request.body(_bodyRequest).post(this.getResource()).then().log().all();
    }

}
