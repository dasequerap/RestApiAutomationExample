package tests;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import java.util.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

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

    protected void validateFieldIsPresent(String fieldName){
        this.getCurrentResponse().assertThat().body("$", hasKey(fieldName));
    }

    protected void validateFieldHasValue(String fieldName, String fieldValue){
        this.getCurrentResponse().body(fieldName, equalTo(fieldValue));
    }

    protected void validateFieldHasNotValue(String fieldName, String fieldValue){
        this.getCurrentResponse().body(fieldName, not(fieldValue));
    }

    protected void validateIntegerFieldIsGreaterThan(String fieldName, int fieldValue){
        this.getCurrentResponse().body(fieldName, greaterThan(fieldValue));
    }

    protected void validateObjectArrayHasSize(String fieldName, int Size){
        this.getCurrentResponse().assertThat().body(fieldName, hasSize(Size));
    }

    protected void validateObjectsInArrayHasFields(String peopleEntry, ArrayList<String> fieldList){
        for(int fieldIndex = 0; fieldIndex < fieldList.size(); fieldIndex++) {
            assertThat(peopleEntry, containsString(fieldList.get(fieldIndex)));
        }
    }
}

