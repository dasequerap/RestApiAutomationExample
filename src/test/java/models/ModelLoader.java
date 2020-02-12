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

    public ModelLoader() throws IOException {
        TestFilesConfigurationReader configurationReader = new TestFilesConfigurationReader();
        String _csvFile = configurationReader.getTestDataFileName(ServiceResources.PEOPLE);
        ResourceReader _peopleData = new ResourceReader( _csvFile, ResourceReader.ProjectResourceDirectory.TEST_DATA);
        String _fileContents = _peopleData.getFileContents();
        _file = _peopleData.getSpecificResourceFile(_csvFile);
        System.out.println( _fileContents );
    }

    private ArrayList<?> getCsvTestData(Class<?> pojoType) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(pojoType);
        ArrayList<Object> testData = new ArrayList<>();

        schema = schema.withColumnSeparator(';').withUseHeader(true)
                .withHeader().withArrayElementSeparator(",");
        ObjectReader reader = mapper.readerFor(pojoType).with(schema);
        MappingIterator<Object> iterator = reader.readValues(_file);

        while (iterator.hasNext()) {
            testData.add(iterator.next());
        }
        return testData;
    }

    public ArrayList<?> getTestPeople() throws IOException {
        return this.getCsvTestData(PeopleModel.class);
    }
}
