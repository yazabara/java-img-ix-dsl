package imgix;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * Created by yazab on 28-Sep-15.
 */
public class CImgIxDownloaderTest {

    private String domain = "zabara.imgix.net";
    private String directory = "images";

    @Test
    public void shouldDownloadImage() throws Exception {
        String ext = "jpg";
        IImgIxBuilder builder = new CImgIxBuilder(domain, "/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png");
        builder.setWidth("480")
                .setHeight("300")
                .setExtension(ext)
                .setQuality("75")
                .setTrim("auto", "black", "9", "8", "")
                .setAuto("enhance")
                .setFit("crop");
        BufferedImage image = CImgIxDownloader.downloadImage(builder.makeLink());
        assertTrue(image != null);

        File outputFile = new File(directory, "savedPathTest." + ext);
        ImageIO.write(image, ext, outputFile);

        assertTrue(outputFile.exists());
    }

    @Test
    public void shouldDownloadImageByBuilder() throws Exception {
        String ext = "jpg";
        IImgIxBuilder builder = new CImgIxBuilder(domain, "/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png");
        builder.setWidth("480")
                .setHeight("300")
                .setExtension(ext)
                .setQuality("75")
                .setTrim("auto", "black", "9", "8", "")
                .setAuto("enhance")
                .setFit("crop");
        BufferedImage image = CImgIxDownloader.downloadImage(builder);
        assertTrue(image != null);

        File outputFile = new File(directory, "savedBuilderTest." + ext);
        ImageIO.write(image, ext, outputFile);

        assertTrue(outputFile.exists());
    }
}