package imgix;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

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
        //TODO make right test (contains params)
        assertTrue(builder.makeLink().equals("http://zabara.imgix.net/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png?w=480&h=300"));
    }

    @Test
    public void builderShouldChangeExt() throws Exception {
        IImgIxBuilder builder = new CImgIxBuilder(domain, "/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png");
        builder.setExtension("jpg");
        //TODO make right test (contains params)
        assertTrue(builder.makeLink().equals("http://zabara.imgix.net/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png?fm=jpg"));
    }

    @Test
    public void builderShouldChangeQuality() throws Exception {
        IImgIxBuilder builder = new CImgIxBuilder(domain, "/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png");
        builder.setQuality("75");
        //TODO make right test (contains params)
        assertTrue(builder.makeLink().equals("http://zabara.imgix.net/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png?q=75"));
    }

    @Test
    public void builderShouldChangeAutoTrim() throws Exception {
        IImgIxBuilder builder = new CImgIxBuilder(domain, "/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png");
        builder.setTrim("auto", "black", "9", "8");
        //TODO make right test (contains params)
        assertTrue(builder.makeLink().equals("http://zabara.imgix.net/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png?trim=auto&trimmd=9&trimsd=8"));
    }

    @Test
    public void builderShouldChangeColorTrim() throws Exception {
        IImgIxBuilder builder = new CImgIxBuilder(domain, "/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png");
        builder.setTrim("color", "black", "9", "8");
        //TODO make right test (contains params)
        assertTrue(builder.makeLink().equals("http://zabara.imgix.net/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png?trim=color&trimcol=black"));
    }

    @Test
    public void builderShouldChangeAuto() throws Exception {
        IImgIxBuilder builder = new CImgIxBuilder(domain, "/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png");
        builder.setAuto("enhance");
        //TODO make right test (contains params)
        assertTrue(builder.makeLink().equals("http://zabara.imgix.net/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png?auto=enhance"));
    }

    @Test
    public void builderShouldChangeFit() throws Exception {
        IImgIxBuilder builder = new CImgIxBuilder(domain, "/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png");
        builder.setFit("crop");
        //TODO make right test (contains params)
        assertTrue(builder.makeLink().equals("http://zabara.imgix.net/channel-CMB_CH001/movie-IDBATN7Z/poster-IDBATN7Z-auto-00001.png?fit=crop"));
    }
}