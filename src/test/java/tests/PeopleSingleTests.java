package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import views.PeopleView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class PeopleSingleTests extends BaseTests{

    private final PeopleView peopleView = new PeopleView();

    PeopleSingleTests() throws IOException {
        this.setCurrentResponse(peopleView.getPeople(1));
        ArrayList<String> peopleResponseFields = new ArrayList<>(Arrays
                .asList( "name", "height", "mass" ));
    }

    @Order(10)
    @Test
    @DisplayName("Check if response is successful for specifics people")
    void checkSpecificPeopleResponseOkTest() {
        this.setCurrentResponse(peopleView.getPeopleById(1));
        this.validateResponseStatusOK();
    }

    @Order(11)
    @Test
    @DisplayName("Check if response format is JSON for specific people")
    void checkSpecificPeopleResponseFormat(){
        this.setCurrentResponse(peopleView.getPeopleById(1));
        this.validateContentTypeIsJson();
    }
}
