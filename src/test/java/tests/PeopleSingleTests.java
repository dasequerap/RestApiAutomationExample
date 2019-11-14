package tests;

import helpers.config.MappingReader;
import models.PeopleModel;
import views.PeopleView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;

class PeopleSingleTests extends BaseTests{

    private final PeopleView peopleView = new PeopleView();
    private final int peopleId = 1;
    private final MappingReader mappingReader = new MappingReader();
    private final MappingReader.Resources peopleResource = MappingReader
            .Resources.PEOPLE;
    private ArrayList<String> peopleSingleResponseFields = new ArrayList<>();
    private PeopleModel people = null;

    PeopleSingleTests() throws IOException {
        this.setCurrentResponse(peopleView.getPeopleById(peopleId));
        this.people = (PeopleModel) this.getCurrentResponse().extract()
                .jsonPath().getObject("$", PeopleModel.class);
        this.peopleSingleResponseFields = mappingReader
                .getMandatoryFieldNames(this.peopleResource, RequestMethod.GET, true );
    }

    @Order(1)
    @Test
    @DisplayName("Check if response is successful for specifics people")
    void checkSpecificPeopleResponseOkTest() {
        this.setCurrentResponse(peopleView.getPeopleById(peopleId));
        this.validateResponseStatusByCode(
                mappingReader.getExpectedResponseCode(this.peopleResource, HttpMethod.GET));
    }

    @Order(2)
    @Test
    @DisplayName("Check if response format is JSON for specific people")
    void checkSpecificPeopleResponseFormat(){
        this.setCurrentResponse(peopleView.getPeopleById(1));
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
    @Order(3)
    @DisplayName("Check if mandatory fields are not null or empty")
    void checkIfPeopleMandatoryFieldsAreNotNullOrEmpty(){
        for(String field: peopleSingleResponseFields){
            this.validateFieldHasNotValue(field, null);
        }
    }
}
