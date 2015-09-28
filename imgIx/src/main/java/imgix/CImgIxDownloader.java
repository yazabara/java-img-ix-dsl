package imgix;

import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by yazab on 28-Sep-15.
 */
public class CImgIxDownloader {

    public static BufferedImage downloadImage(String url) throws IOException {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        return ImageIO.read(new URL(url));
    }

    public static BufferedImage downloadImage(IImgIxBuilder builder) throws IOException {
        String url;
        try {
            url = builder.makeLink();
        } catch (Exception e) {
            return null;
        }
        if (StringUtils.isBlank(url)) {
            return null;
        }
        return ImageIO.read(new URL(url));
    }
}
