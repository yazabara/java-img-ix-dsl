package imgix;

import com.google.gson.Gson;
import imgix.palette.CPaletteJson;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
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

    public static CPaletteJson downloadPaletteJson(String url) throws IOException {
        if (StringUtils.isBlank(url) || !StringUtils.contains(url, CImgIxBuilder.ImgIxParams.PALETTE.getParamName() + "=json")) {
            return null;
        }
        String genreJson = IOUtils.toString(new URL(url).openStream());
        return new Gson().fromJson(genreJson, CPaletteJson.class);
    }

    public static CPaletteJson downloadPaletteJson(IImgIxBuilder builder) throws IOException {
        try {
            return (builder != null && builder.isJsonPalette()) ? downloadPaletteJson(builder.makeLink()) : null;
        } catch (Exception e) {
            return null;
        }
    }
}
