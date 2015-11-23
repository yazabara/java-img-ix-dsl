package youtube;

/**
 * @author Yaroslav Zabara <yaroslav.zabara@waveaccess.ru>
 */
public interface IYoutubeDownloader {

    /**
     * Method try to download video from youtube.
     * throws runtime errors.
     *
     * @return file name (title + ext)
     */
    String download();
}
