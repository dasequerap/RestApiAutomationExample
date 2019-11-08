package tests;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import java.util.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

class BaseTests {

    private ValidatableResponse response;

    BaseTests() { }

    void setCurrentResponse(ValidatableResponse response){
        this.response = response;
    }

    ValidatableResponse getCurrentResponse(){  return this.response; }

    private void validateResponseStatus(int currentStatusCode) {
        this.getCurrentResponse().log().all().assertThat()
                .statusCode(currentStatusCode);
    }

    private void validateContentType(ContentType contentType){
        this.getCurrentResponse().assertThat().contentType(contentType);
    }

    void validateResponseStatusOK(){
        this.validateResponseStatus(HttpStatus.SC_OK);
    }

    void validateResponseStatusIsMethodNotAllowed() { this.validateResponseStatus(HttpStatus.SC_METHOD_NOT_ALLOWED); }

    void validateResponseStatusIsForbidden() { this.validateResponseStatus(HttpStatus.SC_FORBIDDEN); }

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

