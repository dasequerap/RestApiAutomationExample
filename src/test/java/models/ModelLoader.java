package models;

import java.util.ArrayList;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import helpers.ResourceReader;
import java.io.*;

public class ModelLoader{
    private ResourceReader _peopleData;
    private InputStream _file;

    public ModelLoader() throws IOException {
        String _csvFile = "People.csv";
        ResourceReader _peopleData = new ResourceReader( _csvFile, ResourceReader.ProjectResource.TEST_DATA);
        String _fileContents = _peopleData.getFileContents();
        _file = _peopleData.getFile( _csvFile, ResourceReader.ProjectResource.TEST_DATA);
        System.out.println( _fileContents );
    }

    public void getTestPeople() throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(PeopleModel.class);
        schema = schema.withColumnSeparator(';').withUseHeader(true)
                .withHeader().withArrayElementSeparator(",");
        ObjectReader reader = mapper.readerFor(PeopleModel.class).with(schema);
        MappingIterator<PeopleModel> iterator = reader.readValues(_file);
        ArrayList<PeopleModel> testPeople = new ArrayList<>();

        while (iterator.hasNext()) {
            testPeople.add(iterator.next());
        }

        for(PeopleModel people: testPeople){
            System.out.println(people.getName());
        }
    }
}
