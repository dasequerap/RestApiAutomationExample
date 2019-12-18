package tests.people;

import helpers.config.MappingReader;
import models.PeopleModel;
import models.ModelLoader;
import tests.BaseTests;
import views.PeopleView;
import org.junit.jupiter.api.*;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.IOException;
import java.util.ArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PeopleSingleTests extends BaseTests {

    private final MappingReader mappingReader = new MappingReader();
    private final MappingReader.Resources peopleResource = MappingReader
            .Resources.PEOPLE;
    private ArrayList<String> peopleSingleResponseFields;

    PeopleSingleTests() throws IOException {
        int peopleId = 1;
        PeopleView peopleView = new PeopleView();
        this.setCurrentResponse( peopleView.getPeopleById( peopleId ));
        /*this.people = (PeopleModel) this.getCurrentResponse().extract()
                .jsonPath().getObject("$", PeopleModel.class);*/
        this.peopleSingleResponseFields = mappingReader
                .getMandatoryFieldNames(this.peopleResource, RequestMethod.GET, true );
    }

    @Order(1)
    @Test
    @DisplayName("Check if response is successful for specific people")
    void checkSpecificPeopleResponseOkTest() {
        this.validateResponseStatusByCode(
                mappingReader.getExpectedResponseCode(this.peopleResource, HttpMethod.GET));
    }

    @Order(2)
    @Test
    @DisplayName("Check if response format is JSON for specific people")
    void checkSpecificPeopleResponseFormat(){
        this.validateContentTypeIsJson();
    }

    @Test
    @Order(3)
    @DisplayName("Check if single people query has mandatory fields")
    void checkIfPeopleResponseHasMandatoryFields(){
        for(String field: peopleSingleResponseFields){
            this.validateFieldIsPresent(field);
        }
    }

    @Test
    @Order(4)
    @DisplayName("Check if mandatory fields are not null or empty")
    void checkIfPeopleMandatoryFieldsAreNotNullOrEmpty(){
        for(String field: peopleSingleResponseFields){
            this.validateFieldHasNotValue(field, null);
        }
    }

    @Test
    @Order(5)
    @DisplayName("Compare test data against service query")
    void comparePeopleTestDataAgainstRestQuery() throws IOException {
        ModelLoader testDataLoader = new ModelLoader();
        int testSpecificPeopleId = 0;
        PeopleModel testDataPeople = testDataLoader.getTestPeople()
                .get( testSpecificPeopleId );
        PeopleModel specificPeople = this.getCurrentResponse().extract().jsonPath()
                .getObject( "$", PeopleModel.class );
        assertThat(testDataPeople, samePropertyValuesAs(specificPeople));
    }

}
