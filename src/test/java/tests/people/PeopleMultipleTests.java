package tests.people;

import models.PeopleModel;
import tests.BaseTests;
import views.PeopleView;
import helpers.config.MappingReader;
import helpers.constants.Configurations.ServiceResources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PeopleMultipleTests extends BaseTests {

    private final PeopleView peopleView = new PeopleView();
    private final ArrayList<String> peopleSingleResponseFields;
    private final ArrayList<String> peopleMultipleResponseFields;
    private final int pages = 6;
    private int currentPage = 1;

    public PeopleMultipleTests() throws IOException {
        this.setCurrentResponse(peopleView.getPeople(1));
        MappingReader mapping = new MappingReader();
        MappingReader.Resources peopleResource = MappingReader
                .Resources.PEOPLE;
        this.peopleSingleResponseFields = mapping
                .getMandatoryFieldNames(peopleResource, RequestMethod.GET, true);
        this.peopleMultipleResponseFields = mapping
                .getMandatoryFieldNames(peopleResource, RequestMethod.GET, false);
    }

    private void setCurrentPage(int page) { this.currentPage = page; }

    private void setRandomPage() {
        this.setCurrentPage((int) (Math.random() * this.pages) + 2);
    }

    @Order(1)
    @Test
    @DisplayName("Check if response body has mandatory fields")
    void checkMandatoryFieldsForAllPeopleResponse() {
        for(String field: this.peopleMultipleResponseFields){
            this.validateFieldIsPresent(field);
        }
    }

    @Order(2)
    @Test
    @DisplayName("Check if value of \"count\" field is not null or zero")
    void checkCountField(){
        this.validateFieldHasNotValue("count", null);
        this.validateIntegerFieldIsGreaterThan("count", 0);
    }

    @Order(3)
    @Test
    @DisplayName("Check if value of \"results\" field is not null or zero")
    void checkDefaultValueOfResultsField(){
        this.validateFieldHasNotValue("results", null);
    }

    @Order(4)
    @Test
    @DisplayName("Check if \"results\" field has ten people entries")
    void checkIfResultHasTenEntriesForFirstPage(){
        this.validateObjectArrayHasSize("results", 10);
    }

    @Order(5)
    @Test
    @DisplayName("Check if people objects in \"results\" field has mandatory fields")
    void checkIfPeopleEntryInResultsHasMandatoryFields(){
        this.validateObjectsInArrayHasFields(this.peopleView.getResults(), peopleSingleResponseFields);
    }

    @Order(6)
    @Test
    @DisplayName("Check if mandatory fields for people objects in \"results\" field are filled in")
    void checkIfPeopleEntriesHasMandatoryFieldsFilledIn(){
        List<PeopleModel> peopleObjects = this.getCurrentResponse().extract().jsonPath()
                .getList("results", PeopleModel.class);
        for(PeopleModel people : peopleObjects){
            assertThat(people.getName(), notNullValue());
            assertThat(people.getHeight(), notNullValue());
            assertThat(people.getMass(), notNullValue());
        }
    }

    @Order(7)
    @Test
    @DisplayName("Check if value of \"next\" field is not null or empty for first page")
    void checkDefaultValueOfNextFieldForFirstPage(){
        this.validateFieldHasNotValue("next", null);
        this.validateFieldHasNotValue("next", "");
    }

    @Order(8)
    @Test
    @DisplayName("Check if default value of \"previous\" field value is null first page")
    void checkDefaultValueOfPreviousFieldForFirstPage(){
        this.validateFieldHasValue("previous", null);
    }

    @Order(9)
    @Test
    @DisplayName("Check if value of \"previous\" field is not null or empty for a page between first and last")
    void checkValueOfNextFieldForAPageBetweenFirstAndLast(){
        this.setRandomPage();
        this.setCurrentResponse(peopleView.getPeople(currentPage));
        this.validateFieldHasNotValue("previous", null);
        this.validateFieldHasNotValue("previous", "");
    }

    @Order(10)
    @Test
    @DisplayName("Check if value of \"next\" field value is not null or empty for a page between first and last")
    void checkValueOfPreviousFieldForAPageBetweenFirstAndLast(){
        this.setRandomPage();
        this.setCurrentResponse(peopleView.getPeople(currentPage));
        this.validateFieldHasNotValue("next", null);
        this.validateFieldHasNotValue("next", "");
    }

    @Order(11)
    @Test
    @DisplayName("Check if value of \"previous\" field value is not null or empty for a page between first and last")
    void checkValueOfPreviousFieldForLastPage(){
        this.setCurrentPage(this.pages + 3);
        this.setCurrentResponse(peopleView.getPeople(currentPage));
        this.validateFieldHasNotValue("previous", null);
        this.validateFieldHasNotValue("previous", "");
    }

    @Order(12)
    @Test
    @DisplayName("Check if value of \"next\" field is not null or empty for a page between first and last")
    void checkValueOfNextFieldForLastPage(){
        this.setCurrentPage(this.pages + 3);
        this.setCurrentResponse(peopleView.getPeople(currentPage));
        this.validateFieldHasValue("next", null);
    }

    @Order(13)
    @Test
    @DisplayName("Check if response for an invalid page is \"Not Found\"")
    void checkResponseForAnInvalidPage(){
        this.setCurrentPage(this.pages + 4);
        this.setCurrentResponse(peopleView.getPeople(currentPage));
        this.validateFieldHasValue("detail", "Not found");
    }

    @Order(14)
    @Test
    @DisplayName("Check if URL value of \"next\" field is accessible and returns a JSON response")
    void checkNextFieldURLValidity(){
        this.setRandomPage();
        this.setCurrentResponse(peopleView.getPeople(currentPage));
        this.setCurrentResponse(peopleView.goToNextURL());
        this.validateResponseStatusByCode(200);
        this.validateContentTypeIsJson();
    }

    @Order(15)
    @Test
    @DisplayName("Check if URL value of \"next\" field is accessible and returns a JSON response")
    void checkPreviousFieldURLValidity(){
        this.setRandomPage();
        this.setCurrentResponse(peopleView.getPeople(currentPage));
        this.setCurrentResponse(peopleView.goToPreviousURL());
        this.validateResponseStatusByCode(200);
        this.validateContentTypeIsJson();
    }

}