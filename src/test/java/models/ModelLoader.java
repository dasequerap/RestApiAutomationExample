package models;

import java.io.*;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import helpers.config.ResourceReader;
import helpers.config.TestFilesConfigurationReader;
import helpers.constants.Configurations.ServiceResources;

public class ModelLoader{
    private InputStream _file;
    private TestFilesConfigurationReader configurationReader;

    public ModelLoader() throws IOException {
        configurationReader = new TestFilesConfigurationReader();
        String _csvFile = configurationReader.getTestDataFileName(ServiceResources.PEOPLE);
                ResourceReader _peopleData = new ResourceReader( _csvFile, ResourceReader.ProjectResourceDirectory.TEST_DATA);
        String _fileContents = _peopleData.getFileContents();
        _file = _peopleData.getSpecificResourceFile(_csvFile);
        System.out.println( _fileContents );
    }

    public ArrayList<PeopleModel> getTestPeople() throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(PeopleModel.class);
        schema = schema.withColumnSeparator(';').withUseHeader(true)
                .withHeader().withArrayElementSeparator(",");
        ArrayList<PeopleModel> testPeople = new ArrayList<>();
        ObjectReader reader = mapper.readerFor(PeopleModel.class).with(schema);
        MappingIterator<PeopleModel> iterator = reader.readValues(_file);

        while (iterator.hasNext()) {
            testPeople.add(iterator.next());
        }
        return testPeople;
    }
}
