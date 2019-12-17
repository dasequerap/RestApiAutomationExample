package helpers.config;

import helpers.constants.Configurations.TestDataFiles;
import helpers.config.ConfigurationReader;

import java.io.IOException;

public class TestFilesLoader {

    ConfigurationReader configuration;

    public TestFilesLoader() {
        configuration = new ConfigurationReader();
    }

    public String getPeopleTestDataAddress() throws IOException {
        return this.configuration.getTestFilesDirectory() + this.configuration.getKeysForTestFiles()
            .getString(TestDataFiles.PEOPLE.toString());
    }

    public String getFilmsTestDataAddress() throws IOException {
        return this.configuration.getTestFilesDirectory() + this.configuration.getKeysForTestFiles()
                .getString(TestDataFiles.FILMS.toString());
    }

}
