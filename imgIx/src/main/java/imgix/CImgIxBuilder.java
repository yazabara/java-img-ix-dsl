package imgix;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 * Created by yazab on 28-Sep-15.
 */
public class CImgIxBuilder implements IImgIxBuilder {
    public enum ImgIxParams {
        WIDTH("w"),
        HEIGHT("h"),
        AUTO("auto"),
        NEW_EXTENSION("fm"),
        TRIM("trim"),
        FIT("fit"),
        BLUR("blur"),
        PALETTE("palette"),
        MONO("mono"),
        TRIMMD("trimmd"),
        TRIMSD("trimsd"),
        TRIMCOL("trimcol"),
        TRIMTOL("trimtol"),
        QUALITY("q");
        private String paramName;

        ImgIxParams(String paramName) {
            this.paramName = paramName;
        }

        public String getParamName() {
            return paramName;
        }
    }

    private enum ImgIxSchemes {
        HTTP("http"), HTTPS("https");
        private String scheme;

        ImgIxSchemes(String scheme) {
            this.scheme = scheme;
        }
    }

    /**
     * ImgIx domain
     */
    private String domain;
    /**
     * img full path with extension
     */
    private String imagePath;
    /**
     * Scheme
     */
    private String scheme = ImgIxSchemes.HTTP.scheme;
    // ADDITIONAL FIELDS
    private String width;
    private String height;
    /**
     * resize fit mode
     * The fit parameter controls how the output image is fit to its target dimensions.
     * Valid values are clamp, clip, crop, facearea, fill, max, min, and scale.
     * The default value is clip.
     */
    private String fit;
    /**
     * color palette extraction
     * The palette parameter extracts color values from an image and returns them in the specified format. Palette extraction occurs after image processing.
     * Any adjustments and cropping effects applied will change the color palette response. Valid values are css and json.
     */
    private String palette;
    /**
     * blur
     * Applies a gaussian style blur to your image. Valid values are in the range from 0 – 2000. The default value is 0 which leaves the image unchanged.
     */
    private String blur;
    /**
     * output format
     * The output format to convert the image to.
     */
    private String extension;
    /**
     * output quality
     * Controls the output quality of lossy file formats.
     * Applies when then fm parameter is set to jpg, pjpg, webp, or jxr. Valid values are in the range 0 - 100.
     * The default value is 75.
     */
    private String quality;
    /**
     * The auto parameter allows imgix to make image adjustments, enhancements, and optimizations automatically.
     * This includes auto image file formats, auto enhancement, and red eye removal.
     */
    private String auto;
    /**
     * monochrome
     * Applies an overall monochromatic hue change with a specified three or six digit hex value. Both ff0000 and f00 will result the same red.
     * The monochromatic intensity can be set by using an eight digit hex value, with the first two digits representing the opacity of the color being applied.
     */
    private String monochrome;
    // TRIM
    /**
     * trim image
     * variants: auto and color
     */
    private String trim;
    /**
     * trim color
     * The trimcol parameter defines which color to trim when the TRIM PARAMETER is set to COLOR.
     * The trimcol parameter is only valid when trim is set to color.
     */
    private String trimcol;
    /**
     * trim mean difference
     * The trimmd is the mean difference threshold for the trimming operation, and is only valid when TRIM is set to AUTO.
     * The default value is 11.
     */
    private String trimmd;
    /**
     * trim standard deviation
     * The trimsd parameter defines the standard deviation among pixels within a border, and is only valid when TRIM is set to AUTO.
     * The default value is 10.
     */
    private String trimsd;
    /**
     * trim tolerance
     * The trimtol parameter defines the tolerance for the trim=color operation. This parameter is valid only when trim is set to color.
     * If not specified, the default value is 0.
     */
    private String trimtol;

    public CImgIxBuilder(String domain, String imagePath) {
        this.domain = domain;
        this.imagePath = imagePath;
    }

    public String makeLink() throws URISyntaxException, MalformedURLException {
        URIBuilder uriBuilder = new URIBuilder().setScheme(scheme).setHost(domain).setPath(imagePath);
        //additional params
        if (StringUtils.isNotBlank(blur)) {
            uriBuilder.addParameter(ImgIxParams.BLUR.paramName, blur);
        }
        if (StringUtils.isNotBlank(palette)) {
            uriBuilder.addParameter(ImgIxParams.PALETTE.paramName, palette);
        }
        if (StringUtils.isNotBlank(monochrome)) {
            uriBuilder.addParameter(ImgIxParams.MONO.paramName, monochrome);
        }
        if (StringUtils.isNotBlank(width)) {
            uriBuilder.addParameter(ImgIxParams.WIDTH.paramName, width);
        }
        if (StringUtils.isNotBlank(height)) {
            uriBuilder.addParameter(ImgIxParams.HEIGHT.paramName, height);
        }
        if (StringUtils.isNotBlank(extension)) {
            uriBuilder.addParameter(ImgIxParams.NEW_EXTENSION.paramName, extension);
        }
        if (StringUtils.isNotBlank(quality)) {
            uriBuilder.addParameter(ImgIxParams.QUALITY.paramName, quality);
        }
        if (StringUtils.isNotBlank(trim)) {
            uriBuilder.addParameter(ImgIxParams.TRIM.paramName, trim);
            //different params - depends on trim type
            if (StringUtils.equals(trim, "auto")) {
                if (StringUtils.isNotBlank(trimmd)) {
                    uriBuilder.addParameter(ImgIxParams.TRIMMD.paramName, trimmd);
                }
                if (StringUtils.isNotBlank(trimsd)) {
                    uriBuilder.addParameter(ImgIxParams.TRIMSD.paramName, trimsd);
                }
            } else {
                if (StringUtils.isNotBlank(trimcol)) {
                    uriBuilder.addParameter(ImgIxParams.TRIMCOL.paramName, trimcol);
                }
                if (StringUtils.isNotBlank(trimtol)) {
                    uriBuilder.addParameter(ImgIxParams.TRIMTOL.paramName, trimtol);
                }
            }
        }
        if (StringUtils.isNotBlank(auto)) {
            uriBuilder.addParameter(ImgIxParams.AUTO.paramName, auto);
        }
        if (StringUtils.isNotBlank(fit)) {
            uriBuilder.addParameter(ImgIxParams.FIT.paramName, fit);
        }
        return uriBuilder.build().toString();
    }

    // ADDITIONAL PARAMS :
    public IImgIxBuilder setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public IImgIxBuilder setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public IImgIxBuilder setHttpScheme() {
        this.scheme = ImgIxSchemes.HTTP.scheme;
        return this;
    }

    public IImgIxBuilder setHttpsScheme() {
        this.scheme = ImgIxSchemes.HTTPS.scheme;
        return this;
    }

    public IImgIxBuilder setWidth(String width) {
        this.width = width;
        return this;
    }

    public IImgIxBuilder setHeight(String height) {
        this.height = height;
        return this;
    }

    public IImgIxBuilder setQuality(String quality) {
        this.quality = quality;
        return this;
    }

    public IImgIxBuilder setExtension(String extension) {
        this.extension = extension;
        return this;
    }

    public IImgIxBuilder setFit(String fit) {
        String[] availableFits = {"clamp", "clip", "crop", "facearea", "fill", "max", "min", "and", "scale"};
        if (StringUtils.isBlank(fit) || !ArrayUtils.contains(availableFits, fit)) {
            return this;
        }
        this.fit = fit;
        return this;
    }

    public IImgIxBuilder setAuto(String auto) {
        if (!StringUtils.equals(auto, "format") && !StringUtils.equals(auto, "enhance") && !StringUtils.equals(auto, "redeye")) {
            return this;
        }
        this.auto = auto;
        return this;
    }

    public IImgIxBuilder setPalette(String palette) {
        if (!StringUtils.equals(palette, "css") && !StringUtils.equals(palette, "json")) {
            return this;
        }
        this.palette = palette;
        return this;
    }

    public IImgIxBuilder setBlur(Integer blur) {
        if (blur == null || blur < 0 || blur > 2000) {
            return this;
        }
        this.blur = blur.toString();
        return this;
    }

    public IImgIxBuilder setMonochrome(String monochrome) {
        this.monochrome = monochrome;
        return this;
    }

    public IImgIxBuilder setTrim(String trim, String trimcol, String trimmd, String trimsd, String trimtol) {
        if (!StringUtils.equals(trim, "auto") && !StringUtils.equals(trim, "color")) {
            return this;
        }
        this.trim = trim;
        if (StringUtils.equals(trim, "auto")) {
            this.trimmd = StringUtils.isBlank(trimmd) ? null : trimmd;
            this.trimsd = StringUtils.isBlank(trimsd) ? null : trimsd;
        } else {
            this.trimcol = trimcol;
            this.trimtol = trimtol;
        }
        return this;
    }
}
