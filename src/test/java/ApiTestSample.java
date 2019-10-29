import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import models.PeopleModel;
import org.apache.http.HttpStatus;
import views.PeopleView;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;


public class ApiTestSample {

    public static void main(String[] args) {
        PeopleView peopleURI = new PeopleView();
        String nextURL;
        JsonPath currentResponse;
        List<PeopleModel> peopleObjects;
        PeopleModel testPeople = null;


        peopleURI.getPeople(1).assertThat().contentType(ContentType.JSON).and()
                .statusCode(HttpStatus.SC_OK).and().body("count", equalTo(87));
        testPeople = peopleURI.getPeopleById(1).extract().as(PeopleModel.class);
        currentResponse = peopleURI.getPeople(1).extract().jsonPath();

        while (peopleURI.getNextUrl() != null) {
            peopleObjects = currentResponse.getList("results", PeopleModel.class);
            peopleObjects.forEach((peopleIndex) -> System.out.println(
                    peopleIndex.getName() + " : " + peopleIndex.getUrl()));
            if (peopleURI.getNextUrl() != null) {
                currentResponse = peopleURI.getPeople(peopleURI.getCurrentPage() + 1)
                        .extract().jsonPath();
            }
        }
    }
}
