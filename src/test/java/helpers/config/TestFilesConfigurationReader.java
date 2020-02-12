package helpers.config;

import helpers.constants.Configurations.ServiceResources;
import java.io.IOException;

public class TestFilesConfigurationReader {

    private final ConfigurationReader _configuration;

    public TestFilesConfigurationReader() {
        _configuration = new ConfigurationReader();
    }

    public String getTestDataFileName(ServiceResources file) throws IOException {
        switch(file){
            case PEOPLE:
                return this._configuration.getKeysForTestFiles().getString(ServiceResources.PEOPLE.toString());
            case FILMS:
                return this._configuration.getKeysForTestFiles().getString(ServiceResources.FILMS.toString());
            default:
                return null;
        }
    }

}
