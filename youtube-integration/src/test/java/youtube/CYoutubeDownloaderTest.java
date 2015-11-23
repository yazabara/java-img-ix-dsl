package youtube;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;

/**
 * @author Yaroslav Zabara <yaroslav.zabara@waveaccess.ru>
 */
public class CYoutubeDownloaderTest {


    @Test
    public void testDownload() {
        String path = FileUtils.getTempDirectoryPath();
        CYoutubeDownloader downloader = new CYoutubeDownloader("https://www.youtube.com/watch?v=IxU2r-2XFc8", new File(path));
        String name = downloader.download();
        System.out.println("completed. file is: " + path + name);
    }
}
