package imgix;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by yazab on 28-Sep-15.
 */
public class CImgIxBuilderTest {

    private String domain = "zabara.imgix.net";

    @Test
    public void builderShouldMakeSimpleLink() throws Exception {
        IImgIxBuilder builder = new CImgIxBuilder(domain, "/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png");
        assertTrue(builder.makeLink().equals("http://zabara.imgix.net/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png"));
    }

    @Test
    public void builderShouldChangeSchema() throws Exception {
        IImgIxBuilder builder = new CImgIxBuilder(domain, "/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png");
        builder.setHttpsScheme()
                .setHttpsScheme()
                .setHttpScheme();
        assertTrue(builder.makeLink().equals("http://zabara.imgix.net/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png"));
    }

    @Test
    public void builderShouldChangeSize() throws Exception {
        IImgIxBuilder builder = new CImgIxBuilder(domain, "/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png");
        builder.setWidth("480")
                .setHeight("300");

        assertTrue(StringUtils.equals(getParamByName(builder.makeLink(), CImgIxBuilder.ImgIxParams.WIDTH.getParamName()).getValue(), "480"));
        assertTrue(StringUtils.equals(getParamByName(builder.makeLink(), CImgIxBuilder.ImgIxParams.HEIGHT.getParamName()).getValue(), "300"));
    }

    @Test
    public void builderShouldChangeExt() throws Exception {
        IImgIxBuilder builder = new CImgIxBuilder(domain, "/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png");
        builder.setExtension("jpg");
        assertTrue(StringUtils.equals(getParamByName(builder.makeLink(), CImgIxBuilder.ImgIxParams.NEW_EXTENSION.getParamName()).getValue(), "jpg"));
    }

    @Test
    public void builderShouldChangeQuality() throws Exception {
        IImgIxBuilder builder = new CImgIxBuilder(domain, "/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png");
        builder.setQuality("75");
        assertTrue(StringUtils.equals(getParamByName(builder.makeLink(), CImgIxBuilder.ImgIxParams.QUALITY.getParamName()).getValue(), "75"));
    }

    @Test
    public void builderShouldChangeAutoTrim() throws Exception {
        IImgIxBuilder builder = new CImgIxBuilder(domain, "/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png");
        builder.setTrim("auto", "black", "9", "8", "");
        assertTrue(StringUtils.equals(getParamByName(builder.makeLink(), CImgIxBuilder.ImgIxParams.TRIM.getParamName()).getValue(), "auto"));
        assertTrue(StringUtils.equals(getParamByName(builder.makeLink(), CImgIxBuilder.ImgIxParams.TRIMMD.getParamName()).getValue(), "9"));
        assertTrue(StringUtils.equals(getParamByName(builder.makeLink(), CImgIxBuilder.ImgIxParams.TRIMSD.getParamName()).getValue(), "8"));
        assertFalse(StringUtils.equals(getParamByName(builder.makeLink(), CImgIxBuilder.ImgIxParams.TRIMCOL.getParamName()).getValue(), "black"));
    }

    @Test
    public void builderShouldChangeColorTrim() throws Exception {
        IImgIxBuilder builder = new CImgIxBuilder(domain, "/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png");
        builder.setTrim("color", "black", "9", "8", null);
        assertTrue(StringUtils.equals(getParamByName(builder.makeLink(), CImgIxBuilder.ImgIxParams.TRIM.getParamName()).getValue(), "color"));
        assertFalse(StringUtils.equals(getParamByName(builder.makeLink(), CImgIxBuilder.ImgIxParams.TRIMMD.getParamName()).getValue(), "9"));
        assertFalse(StringUtils.equals(getParamByName(builder.makeLink(), CImgIxBuilder.ImgIxParams.TRIMSD.getParamName()).getValue(), "8"));
        assertTrue(StringUtils.equals(getParamByName(builder.makeLink(), CImgIxBuilder.ImgIxParams.TRIMCOL.getParamName()).getValue(), "black"));
    }

    @Test
    public void builderShouldChangeAuto() throws Exception {
        IImgIxBuilder builder = new CImgIxBuilder(domain, "/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png");
        builder.setAuto("enhance");
        assertTrue(StringUtils.equals(getParamByName(builder.makeLink(), CImgIxBuilder.ImgIxParams.AUTO.getParamName()).getValue(), "enhance"));
    }

    @Test
    public void builderShouldChangeFit() throws Exception {
        IImgIxBuilder builder = new CImgIxBuilder(domain, "/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png");
        builder.setFit("crop");
        assertTrue(StringUtils.equals(getParamByName(builder.makeLink(), CImgIxBuilder.ImgIxParams.FIT.getParamName()).getValue(), "crop"));
    }

    @Test
    public void builderShouldChangeBlur() throws Exception {
        IImgIxBuilder builder = new CImgIxBuilder(domain, "/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png");
        builder.setBlur(1000);
        assertTrue(StringUtils.equals(getParamByName(builder.makeLink(), CImgIxBuilder.ImgIxParams.BLUR.getParamName()).getValue(), "1000"));
    }

    @Test
    public void builderShouldChangeMono() throws Exception {
        IImgIxBuilder builder = new CImgIxBuilder(domain, "/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png");
        builder.setMonochrome("f00");
        assertTrue(StringUtils.equals(getParamByName(builder.makeLink(), CImgIxBuilder.ImgIxParams.MONO.getParamName()).getValue(), "f00"));
    }

    @Test
    public void builderShouldChangePalette() throws Exception {
        IImgIxBuilder builder = new CImgIxBuilder(domain, "/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png");
        builder.setPalette("json");
        assertTrue(StringUtils.equals(getParamByName(builder.makeLink(), CImgIxBuilder.ImgIxParams.PALETTE.getParamName()).getValue(), "json"));
    }

    private NameValuePair getParamByName(String link, String name) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(link);
        for (NameValuePair pair : uriBuilder.getQueryParams()){
            if (StringUtils.equals(pair.getName(), name)) {
                return pair;
            }
        }
        return new NameValuePair() {
            public String getName() {
                return null;
            }

            public String getValue() {
                return null;
            }
        };
    }
}
