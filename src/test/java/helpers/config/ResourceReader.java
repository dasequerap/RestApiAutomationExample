package helpers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import helpers.config.ConfigurationReader;
import org.apache.commons.io.IOUtils;

public class ResourceReader {

    private String fileName;
    private String fileContents;
    private final ProjectResourceDirectory projectResourceDirectory;

    public enum ProjectResourceDirectory {
        CONFIG("./config/"),
        TEST_DATA("./test data/");
        final String resourceDirectoryName;
        ConfigurationReader configuration;

         ProjectResourceDirectory(String resourceDirectoryName) {
            this.resourceDirectoryName = resourceDirectoryName;
        }
    }

    public ResourceReader(String filename, ProjectResourceDirectory resource) throws IOException {
        this.projectResourceDirectory = resource;
        this.setFileName(filename);
        this.setFileContents();
    }

    public InputStream getSpecificResourceFile(String fileName){
        String configDirectory = this.projectResourceDirectory.resourceDirectoryName;
        return getClass().getClassLoader()
                .getResourceAsStream(configDirectory + fileName);
    }

    public String getFileContents() { return this.fileContents; }

    private void setFileName(String fileName){ this.fileName = fileName; }

    private void setFileContents() throws IOException  {
        this.fileContents = IOUtils.toString(this.getSpecificResourceFile(this.fileName), StandardCharsets.UTF_8);
    }

}
