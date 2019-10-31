package tests;

import io.restassured.path.json.JsonPath;
import models.PeopleModel;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import views.PeopleView;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PeopleTests extends BaseTests{

    private String nextURL;
    private JsonPath currentResponse;
    private List<PeopleModel> peopleObjects;
    private PeopleModel testPeople = null;
    private PeopleView peopleView = new PeopleView();

    public PeopleTests(){
        this.setCurrentResponse(peopleView.getPeople(1));
    }

    /* Test Feature Branch */
    @Test
    @Order(1)
    public void checkPeopleResponseOkTest() {
        this.validateResponseStatusOK();
    }

    @Order(2)
    @Test
    public void checkPeopleResponseFormat() {
        this.validateContentTypeIsJson();
    }

    @Order(3)
    @Test
    public void checkSpecificPeopleResponseOkTest() {
        this.setCurrentResponse(peopleView.getPeopleById(1));
        this.validateResponseStatusOK();
    }

    @Order(4)
    @Test
    public void checkSpecificPeopleResponseFormat(){
        this.setCurrentResponse(peopleView.getPeopleById(1));
        this.validateContentTypeIsJson();
    }

}