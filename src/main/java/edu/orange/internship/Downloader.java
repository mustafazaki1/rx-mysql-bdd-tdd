package edu.orange.internship;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KEE on 19-Aug-16.
 */
public class Downloader {
    private final int LENGTH = 1024;

    public List<Request> readFile(String file) throws Exception {
        String line;
        List<Request> result = new ArrayList<>();
        BufferedReader bufferedReader;


        if (!Files.exists(Paths.get(file)))
            throw new FileNotFoundException();
        // Where are you spring ?!
        bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file), StandardCharsets.UTF_8
                )
        );

        while ((line = bufferedReader.readLine()) != null) {
            Request newRequest = new Request();
            String arguments[] = line.split("\\s+");
            if (arguments.length != 2) {
                throw new IllegalArgumentException();
            }
            newRequest.setUri(arguments[0]);
            newRequest.setFilename(arguments[1]);
            result.add(newRequest);
        }

        return result;
    }

    public List<DownloadedBytes> download(Request request) throws IOException{
        List<DownloadedBytes> result = new ArrayList<>();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(
                request.getUri().toURL().openStream()
        );
        byte current[] = new byte[LENGTH];
        int count;

        while ((count = bufferedInputStream.read(current,0,LENGTH)) != -1){
            DownloadedBytes downloadedBytes = new DownloadedBytes();
            downloadedBytes.setFilename(request.getFilename());
            downloadedBytes.setCount(count);
            downloadedBytes.setBytes(current);
            result.add(downloadedBytes);
        }
        return result;
    }

    public void write(DownloadedBytes downloadedBytes) throws IOException{
        FileOutputStream output = new FileOutputStream(downloadedBytes.getFilename(), true);
        output.write(downloadedBytes.getBytes(),0,downloadedBytes.getCount());
    }

}
