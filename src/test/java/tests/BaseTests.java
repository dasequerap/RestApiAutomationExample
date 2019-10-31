package tests;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class BaseTests {

    private ValidatableResponse response;

    protected BaseTests() { }

    protected void setCurrentResponse(ValidatableResponse response){
        this.response = response;
    }

    protected ValidatableResponse getCurrentResponse(){  return this.response; }

    protected void validateResponseStatus(int currentStatusCode) {
        this.getCurrentResponse().log().all().assertThat()
                .statusCode(currentStatusCode);
    };

    protected void validateContentType(ContentType contentType){
        this.getCurrentResponse().assertThat().contentType(contentType);
    }

    protected void validateResponseStatusOK(){
        this.validateResponseStatus(HttpStatus.SC_OK);
    }

    protected void validateContentTypeIsJson() { this.validateContentType(ContentType.JSON); }
}

