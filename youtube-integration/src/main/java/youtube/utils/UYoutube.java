package youtube.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class UYoutube {
    public static final String YOUTUBE_PARAM_NAME = "v";

    /**
     * Method return param value or url
     *
     * @param param
     * @param url
     * @return
     */
    public static String getUrlParamValue(String param, String url) {
        if (StringUtils.isBlank(param)) {
            return null;
        }
        Pattern p = Pattern.compile(param + "=([^&]+)");
        Matcher m = p.matcher(url);
        if (m.find()) {
            return m.group().replace(param + "=", "");
        }
        return null;
    }

    /**
     * Method returns ytId or null
     *
     * @param url
     * @return
     */
    public static String getYoutubeId(String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        String ytID = null;
        if (!isYouTubeUrl(url) && isYouTubeId(url)) {
            ytID = url;
        } else if (isYouTubeUrl(url) && !isYouTubeId(url)) {
            ytID = getUrlParamValue(YOUTUBE_PARAM_NAME, url);
        }
        return ytID;
    }

    /**
     * Method check yt url (regexp)
     *
     * @param url
     * @return
     */
    public static boolean isYouTubeUrl(String url) {
        String pattern = "https?://(?:[0-9A-Z-]+\\.)?(?:youtu\\.be/|youtube\\.com\\S*[^\\w\\-\\s])([\\w\\-]{11})(?=[^\\w\\-]|$)(?![?=&+%\\w]*(?:['\"][^<>]*>|<\\a>))[?=&+%\\w]*$";
        Pattern compiledPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = compiledPattern.matcher(url);
        return matcher.find();
    }

    /**
     * Method check yt ID (regexp)
     *
     * @param url
     * @return
     */
    public static boolean isYouTubeId(String url) {
        String pattern = "^([\\w\\-]{11})$";
        Pattern compiledPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = compiledPattern.matcher(url);
        return matcher.find();
    }

    public static String makeUrl(String id) {
        return "http://www.youtube.com/watch?" + YOUTUBE_PARAM_NAME + "=" + id;
    }
}
