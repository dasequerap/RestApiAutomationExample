package tests;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.apache.http.params.CoreConnectionPNames;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

class BaseTests {

    public ValidatableResponse response;
    BaseTests() { }

    ValidatableResponse getCurrentResponse(){  return this.response; }

    void setCurrentResponse(ValidatableResponse response){
        this.response = response;
    }

    private void validateResponseStatus(int currentStatusCode) {
        this.getCurrentResponse().log().all().assertThat()
                .statusCode(currentStatusCode);
    }

    private void validateContentType(ContentType contentType){
        this.getCurrentResponse().assertThat().contentType(contentType);
    }

    void validateResponseStatusByCode(int statusCode){
        switch(statusCode){
            case HttpStatus.SC_OK:
                this.validateResponseStatus(HttpStatus.SC_OK);
                break;
            case HttpStatus.SC_METHOD_NOT_ALLOWED:
                this.validateResponseStatus(HttpStatus.SC_METHOD_NOT_ALLOWED);
                break;
            case HttpStatus.SC_FORBIDDEN:
                this.validateResponseStatus(HttpStatus.SC_FORBIDDEN);
                break;
            default:
                break;
        }
    }

    void validateContentTypeIsJson() { this.validateContentType(ContentType.JSON); }

    void validateFieldIsPresent(String fieldName){
        this.getCurrentResponse().assertThat().body("$", hasKey(fieldName));
    }

    void validateFieldHasValue(String fieldName, String fieldValue){
        this.getCurrentResponse().body(fieldName, equalTo(fieldValue));
    }

    void validateFieldHasNotValue(String fieldName, String fieldValue){
        this.getCurrentResponse().body(fieldName, not(fieldValue));
    }

    void validateIntegerFieldIsGreaterThan(String fieldName, int fieldValue){
        this.getCurrentResponse().body(fieldName, greaterThan(fieldValue));
    }

    void validateObjectArrayHasSize(String fieldName, int Size){
        this.getCurrentResponse().assertThat().body(fieldName, hasSize(Size));
    }

    void validateObjectsInArrayHasFields(String peopleEntry, ArrayList<String> fieldList){
        for(String field : fieldList){
            assertThat(peopleEntry, containsString(field));
        }
    }

}

