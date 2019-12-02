package helpers;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ResourceReader {

    private String fileName;
    private String fileContents;
    private ProjectResource projectResource;
    private String configDirectory;

    public enum ProjectResource {
        CONFIG("./config/"),
        TEST_DATA("./test data/");
        final String resourceName;

        ProjectResource(String resourceName) {
            this.resourceName = resourceName;
        }
    }

    public ResourceReader(){
        String configDirectory = "./config/";
    }

    public ResourceReader(String filename, ProjectResource resource) throws IOException {
        this.projectResource = resource;
        this.setFileName(filename);
        this.setFileContents(resource);
    }

    private InputStream getFile(String fileName){
        return getClass().getClassLoader()
                .getResourceAsStream( configDirectory + fileName);
    }

    public InputStream getFile(String fileName, ProjectResource resource){
        String configDirectory = this.projectResource.resourceName;
        return getClass().getClassLoader()
                .getResourceAsStream( configDirectory + fileName);
    }

    public String getFileContents() { return this.fileContents; }

    private void setFileName(String fileName){ this.fileName = fileName; }

    protected void setFileContents() throws IOException {
        this.fileContents = IOUtils.toString(this.getFile(this.fileName), StandardCharsets.UTF_8);
    }

    private void setFileContents(ProjectResource resource) throws IOException {
        this.fileContents = IOUtils.toString(this.getFile(this.fileName, projectResource), StandardCharsets.UTF_8);
    }

    public JSONObject getJsonConfig(){
        return new JSONObject(this.getFileContents());
    }

}
