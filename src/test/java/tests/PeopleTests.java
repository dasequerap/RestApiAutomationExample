package tests;

import io.restassured.path.json.JsonPath;
import models.PeopleModel;
import org.junit.jupiter.api.*;
import views.PeopleView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PeopleTests extends BaseTests{

    private String nextURL;
    private JsonPath currentResponse;
    private List<PeopleModel> peopleObjects;
    private PeopleModel testPeople = null;
    private PeopleView peopleView = new PeopleView();
    private ArrayList<String> peopleResponseFields;

    public PeopleTests(){
        this.setCurrentResponse(peopleView.getPeople(1));
        this.peopleResponseFields = new ArrayList<>();
        this.peopleResponseFields.addAll(Arrays.asList("name", "height", "mass"));
    }

    /* Test Feature Branch */
    @Test
    @Order(1)
    @DisplayName("Check if response is successful for all people")
    public void checkPeopleResponseOkTest() {
        this.validateResponseStatusOK();
    }

    @Order(2)
    @Test
    @DisplayName("Check if response format is JSON for all people")
    public void checkPeopleResponseFormat() {
        this.validateContentTypeIsJson();
    }

    @Order(3)
    @Test
    @DisplayName("Check if response boy has mandatory fields")
    public void checkMandatoryFieldsForAllPeopleResponse() {
        this.validateFieldIsPresent("count");
        this.validateFieldIsPresent("previous");
        this.validateFieldIsPresent("next");
        this.validateFieldIsPresent("results");
    }

    @Order(3)
    @Test
    @DisplayName("Check if value of \"count\" field is not null or zero")
    public void checkCountFieldIsNotNullOrZero(){
        this.validateFieldHasNotValue("count", null);
        this.validateIntegerFieldIsGreaterThan("count", 0);
    }

    @Order(4)
    @Test
    @DisplayName("Check if value of \"next\" field is not null or empty")
    public void checkDefaultValueOfNextFieldIsNotNullOrEmpty(){
        this.validateFieldHasNotValue("next", null);
        this.validateFieldHasNotValue("next", "");
    }


    @Order(5)
    @Test
    @DisplayName("Check if default value of \"previous\" field value is null")
    public void checkDefaultValueOfPreviousFieldIsNull(){
        this.validateFieldHasValue("previous", null );
    }

    @Order(6)
    @Test
    @DisplayName("Check if value of \"results\" field is not null or zero")
    public void checkDefaultValueOfResultsFieldIsNotNull(){
        this.validateFieldHasNotValue("results", null);
    }

    @Order(7)
    @Test
    @DisplayName("Check if \"results\" field has ten people entries")
    public void checkIfResultHasTenEntriesForFirstPage(){
        this.validateObjectArrayHasSize("results", 10);
    }

    @Order(8)
    @Test
    @DisplayName("Check if people objects in \"results\" field has mandatory fields")
    public void checkIfPeopleInResultsHasMandatoryFields(){
        this.validateObjectsInArrayHasFields("results", peopleResponseFields);
    }

    @Order(9)
    @Test
    @DisplayName("Check if response is successful for specifics people")
    public void checkSpecificPeopleResponseOkTest() {
        this.setCurrentResponse(peopleView.getPeopleById(1));
        this.validateResponseStatusOK();
    }

    @Order(10)
    @Test
    @DisplayName("Check if response format is JSON for specific people")
    public void checkSpecificPeopleResponseFormat(){
        this.setCurrentResponse(peopleView.getPeopleById(1));
        this.validateContentTypeIsJson();
    }
}