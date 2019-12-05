package helpers;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ResourceReader {

    private String fileName;
    private String fileContents;
    private final ProjectResource projectResource;

    public enum ProjectResource {
        CONFIG("./config/"),
        TEST_DATA("./test data/");
        final String resourceName;

        ProjectResource(String resourceName) {
            this.resourceName = resourceName;
        }
    }

    public ResourceReader(String filename, ProjectResource resource) throws IOException {
        this.projectResource = resource;
        this.setFileName(filename);
        this.setFileContents();
    }

    public InputStream getSpecificResourceFile(String fileName){
        String configDirectory = this.projectResource.resourceName;
        return getClass().getClassLoader()
                .getResourceAsStream( configDirectory + fileName);
    }

    public String getFileContents() { return this.fileContents; }

    private void setFileName(String fileName){ this.fileName = fileName; }

    private void setFileContents() throws IOException  {
        this.fileContents = IOUtils.toString(this.getSpecificResourceFile(this.fileName), StandardCharsets.UTF_8);
    }
}
