package helpers;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ProjectFileReader {

    private String fileName;
    private String directory;

    public ProjectFileReader(String fileName, String directory) {
        this.fileName = fileName;
        this.directory = directory;
    }

    protected String getFileAddress() {
        return directory + fileName;
    }

    protected InputStream loadFile(){
        return getClass().getClassLoader().getResourceAsStream(this.getFileAddress());
    }

    protected String getFileContents() throws IOException {
        return IOUtils.toString(this.loadFile(), StandardCharsets.UTF_8);
    }

    public JSONObject returnFileContentsAsJson() throws IOException {
        return new JSONObject(this.getFileContents());
    }
}
