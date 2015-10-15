package imgix;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 * Created by yazab on 28-Sep-15.
 */
public interface IImgIxBuilder {

    String makeLink() throws URISyntaxException, MalformedURLException;

    IImgIxBuilder setFit(String fit);

    IImgIxBuilder setExtension(String extension);

    IImgIxBuilder setAuto(String auto);

    IImgIxBuilder setHeight(String height);

    IImgIxBuilder setWidth(String width);

    IImgIxBuilder setHttpsScheme();

    IImgIxBuilder setHttpScheme();

    IImgIxBuilder setImagePath(String imagePath);

    IImgIxBuilder setDomain(String domain);

    IImgIxBuilder setQuality(String quality);

    IImgIxBuilder setBlur(Integer blur);

    IImgIxBuilder setMonochrome(String monochrome);

    IImgIxBuilder setPalette(String palette);

    IImgIxBuilder setTrim(String trim, String trimcol, String trimmd, String trimsd, String trimtol);
}
