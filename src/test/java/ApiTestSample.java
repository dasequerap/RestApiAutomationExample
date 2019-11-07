import java.io.IOException;
import java.io.InputStream;
import helpers.config.MappingReader;

public class ApiTestSample {

    public static void main(String[] args) throws IOException {

        MappingReader configReader = new MappingReader();
        System.out.println(configReader.getRawConfig());
        System.out.println(configReader.getPeopleURI());
        System.out.println(configReader.getPeopleURIParams());
        System.out.println(configReader.getPeopleURIParams().length());
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

    private InputStream getMappingFile(){
        return getClass().getClassLoader().getResourceAsStream("./config/mapping.json");
    }
}
