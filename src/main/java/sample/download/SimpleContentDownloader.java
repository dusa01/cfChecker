package sample.download;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CancellationException;

public class SimpleContentDownloader {

    private static final Logger log = LoggerFactory.getLogger(SimpleContentDownloader.class);
    private static final int BUFFER_SIZE = 4096;
    private URL url;
    private String cookie;
    private String downloadDestFile;
    private long startByte = 0;
    private int downloadByte;

    public SimpleContentDownloader(URL url, String cookie, String downloadDestFile, int downloadByte) {
        this.url = url;
        this.cookie = cookie;
        this.downloadDestFile = downloadDestFile;
        this.downloadByte = downloadByte;
    }

    public Boolean download() throws Exception {
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Cookie", this.cookie);

            try (RandomAccessFile raf = new RandomAccessFile(downloadDestFile, "rw")) {
                if (log.isDebugEnabled()) {
                    if (conn.getResponseCode() / 100 != 2) {
                        log.debug("http status : " + conn.getResponseCode());
                    }
                }
                try (BufferedInputStream in = new BufferedInputStream(conn.getInputStream())) {
                    raf.seek(startByte);
                    byte data[] = new byte[BUFFER_SIZE];
                    int numRead;
                    while (((numRead = in.read(data, 0, BUFFER_SIZE)) != -1)) {
                        if (Thread.interrupted()) {
                            throw new CancellationException();
                        }
                        raf.write(data, 0, numRead);
                        downloadByte += numRead;
                    }
                }
            }
        } catch (CancellationException e) {
            return new Boolean(false);
        }
        return new Boolean(true);
    }
}
