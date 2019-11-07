import java.io.IOException;
import java.io.InputStream;
import helpers.config.MappingReader;
import org.springframework.web.bind.annotation.RequestMethod;

public class ApiTestSample {

    public static void main(String[] args) throws IOException {

        MappingReader configReader = new MappingReader();
        System.out.println(configReader.getRawConfig());
        System.out.println(configReader.getServiceURI());
        System.out.println(configReader.getResource(MappingReader.Resources.PEOPLE));
        System.out.println(configReader.getConfig(MappingReader.Resources.PEOPLE));
        System.out.println(configReader.getQueryParametersByMethod(MappingReader.Resources.PEOPLE, RequestMethod.GET));
        System.out.println(configReader.getRequestQueryParameters(MappingReader.Resources.PEOPLE, RequestMethod.GET));
        System.out.println(configReader.getFullResourceURI(MappingReader.Resources.PEOPLE));
        /*PeopleView peopleURI = new PeopleView();
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
        }*/
    }
}
