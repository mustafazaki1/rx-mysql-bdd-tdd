package edu.orange.internship;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by Mustafa on 8/1/2016.
 */
public class DownloaderTest {

    @Test
    public void invalid_url() {
        String[] URLS = {
                "Mustafa Zaki",
                "khaled kee",
                "Yusufe Hussin",
                "1243436564",
                "dfgkfpgd"
        };
        for (String url : URLS) {
            Boolean result = new Downloader().enterUrl(url);
            assertEquals(false, result);
        }
    }

    @Test
    public void valid_url() {
        String[] URLS = {
                "http://stackoverflow.com/questions/163360/regular-expression-to-match-urls-in-java",
                "https://docs.google.com/spreadsheets/d/1Cc-gCUqx4wWHepvNBRmrgydNrCwZ085GQp-IrxauXnA/edit#gid=823786479",
                "https://outlook.office.com/owa/?realm=cis.asu.edu.eg&exsvurl=1&ll-cc=1033&modurl=0",
                "https://github.com/mustafazaki1/rx-mysql-bdd-tdd"
        };
        for (String url : URLS) {
            Boolean result = new Downloader().enterUrl(url);
            assertEquals(true, result);
        }
    }

    @Test
    public void download() throws Exception{
        String urlString = "http://www.ecs.umass.edu/ece/ece122/lectures/lect16/RationalNumber.java",
                expectedfile = "i.html",
                actualfile = "1.html";
        Downloader downloader = new Downloader();
        downloader.download(actualfile);

        BufferedInputStream expected = null,actual = null;
        try {
            expected = new BufferedInputStream(new FileInputStream(expectedfile));
            actual = new BufferedInputStream(new FileInputStream(actualfile));
            final byte fromExpected[] = new byte[1024],fromActual[] = new byte[1024];
            int countExpected = -1,countActual = -1;

            while(true){
                countExpected = expected.read(fromExpected,0,1024);
                countActual = actual.read(fromActual,0,1024);
                assertEquals(countExpected,countActual);
                if(countActual == -1)
                    break;
                for(int index = 0;index < countActual;index++){
                    assertEquals(fromExpected[index],fromActual[index]);
                }
            }

        } finally {
            if(expected != null){
                expected.close();
            }
            if(actual != null){
                actual.close();
            }
        }
    }

}
