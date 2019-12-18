package helpers.config;

import helpers.constants.Configurations.ServiceResources;
import java.io.IOException;

public class TestFilesConfigurationReader {

    ConfigurationReader configuration;

    public TestFilesConfigurationReader() {
        configuration = new ConfigurationReader();
    }

    public String getTestDataFileName(ServiceResources file) throws IOException {
        switch(file){
            case PEOPLE:
                return this.configuration.getKeysForTestFiles().getString(ServiceResources.PEOPLE.toString());
            case FILMS:
                return this.configuration.getKeysForTestFiles().getString(ServiceResources.FILMS.toString());
            default:
                return null;
        }
    }

    public String getTestDataFileAddress(ServiceResources file) throws IOException {
        switch(file){
            case PEOPLE:
                return this.configuration.getTestFilesDirectory() + this.configuration.getKeysForTestFiles()
                        .getString( ServiceResources.PEOPLE.toString());
            case FILMS:
                return this.configuration.getTestFilesDirectory() + this.configuration.getKeysForTestFiles()
                        .getString( ServiceResources.FILMS.toString());
            default:
                return null;
        }
    }

}
