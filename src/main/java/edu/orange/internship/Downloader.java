package edu.orange.internship;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by KEE on 19-Aug-16.
 */
public class Downloader {
    private final int LENGTH = 2048;

    public Observable<Request> readFile(String file) throws IOException {
        Observable<Request> result;
        BufferedReader bufferedReader;


        if (!Files.exists(Paths.get(file)))
            throw new FileNotFoundException();
        // Where are you spring ?!
        bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file), StandardCharsets.UTF_8
                )
        );

        result = Observable.create(subscriber -> {
            String line;
            try {

                while ((line = bufferedReader.readLine()) != null) {
                    Request newRequest = new Request();
                    String arguments[] = line.trim().split("\\s+");
                    if (arguments.length != 2) {
                        System.err.println("Wrong formatted line: " + line);
                        continue;
                    }
                    try {
                        newRequest.setUri(arguments[0]);
                    }catch (URISyntaxException ex){
                        System.err.println("Wrong URI: " + arguments[0]);
                        continue;
                    }
                    newRequest.setFilename(arguments[1]);
                    subscriber.onNext(newRequest);
                }
                subscriber.onCompleted();
            }catch (IOException ex){
                subscriber.onError(ex);
            }
        });

        result.subscribeOn(Schedulers.io());
        return result;
    }

    public Observable download (final Request request){
        return read(request)
                .concatMap(downloadedBytes -> write(request,downloadedBytes));
    }

    public Observable<DownloadedBytes> read(final Request request) {
        Observable<DownloadedBytes> result = Observable.create(
                subscriber -> {
                    try (BufferedInputStream bufferedInputStream = new BufferedInputStream(
                            request.getUri().toURL().openStream()
                    )) {
                        byte current[] = new byte[LENGTH];
                        int count;

                        while ((count = bufferedInputStream.read(current, 0, LENGTH)) != -1) {
                            DownloadedBytes downloadedBytes = new DownloadedBytes();
                            downloadedBytes.setCount(count);
                            downloadedBytes.setBytes(current);
                            subscriber.onNext(downloadedBytes);
                        }
                        subscriber.onCompleted();
                    } catch (IOException ex) {
                        subscriber.onError(ex);
                    }
                });
        result.subscribeOn((Schedulers.io()));
        return result;
    }

    public Observable write(final Request request,final DownloadedBytes downloadedBytes) {
        return Observable.create(subscriber -> {
            try (FileOutputStream output = new FileOutputStream(request.getFilename(), true)){
                output.write(downloadedBytes.getBytes(), 0, downloadedBytes.getCount());
                subscriber.onCompleted();
            }catch (IOException ex){
                subscriber.onError(ex);
            }
        }).subscribeOn(Schedulers.io());
    }

}
