package tests;

import models.PeopleModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import views.PeopleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PeopleSingleTests extends BaseTests{

    private PeopleView peopleView = new PeopleView();
    private ArrayList<String> peopleResponseFields;

    public PeopleSingleTests(){
        this.setCurrentResponse(peopleView.getPeople(1));
        this.peopleResponseFields = new ArrayList<>();
        this.peopleResponseFields.addAll( Arrays.asList("name", "height", "mass"));
    }

    @Order(10)
    @Test
    @DisplayName("Check if response is successful for specifics people")
    public void checkSpecificPeopleResponseOkTest() {
        this.setCurrentResponse(peopleView.getPeopleById(1));
        this.validateResponseStatusOK();
    }

    @Order(11)
    @Test
    @DisplayName("Check if response format is JSON for specific people")
    public void checkSpecificPeopleResponseFormat(){
        this.setCurrentResponse(peopleView.getPeopleById(1));
        this.validateContentTypeIsJson();
    }
}
