package imgix.palette;

import java.util.List;

/**
 * Created by yazab on 15-Oct-15.
 * <p>
 * The response will be a JSON object with the application/json MIME type.
 * The JSON object has two top-level properties: a floating-point average_luminance value, and a colors array of colors, sorted from most to least prominent.
 * Each color in the colors array is a hash describing its red, green, and blue components, expressed as floating point numbers between 0.0 and 1.0.
 */
public class CPaletteJson {

    private Float average_luminance;
    private List<CColor> colors;

    public CPaletteJson() {
    }

    public Float getAverage_luminance() {
        return average_luminance;
    }

    public void setAverage_luminance(Float average_luminance) {
        this.average_luminance = average_luminance;
    }

    public List<CColor> getColors() {
        return colors;
    }

    public void setColors(List<CColor> colors) {
        this.colors = colors;
    }
}
