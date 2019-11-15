package models;

import helpers.ResourceReader;
import net.sf.jsefa.Deserializer;
import net.sf.jsefa.csv.CsvIOFactory;
import java.io.*;

public class ModelLoader{
    private String _fileContents;

    public ModelLoader() throws IOException {
        String _csvFile = "People.csv";
        ResourceReader _peopleData = new ResourceReader( _csvFile, ResourceReader.ProjectResource.TEST_DATA );
        _fileContents = _peopleData.getFileContents();
        System.out.println( _fileContents );
    }

    public void getTestPeople(){
        Deserializer deserializer = CsvIOFactory.createFactory(PeopleModel.class).createDeserializer();
        StringReader reader = new StringReader( _fileContents );
        deserializer.open(reader);
        while (deserializer.hasNext()) {
            PeopleModel people = deserializer.next();
        }
        deserializer.close(true);
    }
}
