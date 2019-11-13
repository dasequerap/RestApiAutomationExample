package tests;

import helpers.config.MappingReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RequestMethod;
import views.PeopleView;

import java.io.IOException;
import java.util.ArrayList;

class PeopleSingleTests extends BaseTests{

    private final PeopleView peopleView = new PeopleView();

    PeopleSingleTests() throws IOException {
        MappingReader mapping = new MappingReader();
        MappingReader.Resources peopleResource = MappingReader
                .Resources.PEOPLE;
        this.setCurrentResponse(peopleView.getPeople(1));
        ArrayList<String> peopleSingleResponseFields = mapping
                .getMandatoryFieldNames( peopleResource, RequestMethod.GET, true );
    }

    @Order(10)
    @Test
    @DisplayName("Check if response is successful for specifics people")
    void checkSpecificPeopleResponseOkTest() {
        this.setCurrentResponse(peopleView.getPeopleById(1));
        this.validateResponseStatusByCode(200);
    }

    @Order(11)
    @Test
    @DisplayName("Check if response format is JSON for specific people")
    void checkSpecificPeopleResponseFormat(){
        this.setCurrentResponse(peopleView.getPeopleById(1));
        this.validateContentTypeIsJson();
    }
}
