package edu.orange.internship;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by KEE on 19-Aug-16.
 */
public class Request {
    private URI uri;

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public void setUri(String uri) throws URISyntaxException{
        this.uri = new URI(uri);
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        String extension = filename.substring(filename.lastIndexOf('.'));
        String fileNameWithoutExtn = filename.substring(0, filename.lastIndexOf('.'));
        Integer value = 0;
        while (Files.exists(Paths.get(filename))){
            filename = fileNameWithoutExtn + value.toString() + extension;
        }
        this.filename = filename;
    }

    private String filename;

}
