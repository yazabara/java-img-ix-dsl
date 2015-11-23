package youtube;

import com.github.axet.vget.VGet;
import com.github.axet.vget.info.VGetParser;
import com.github.axet.vget.info.VideoInfo;
import com.github.axet.vget.vhs.VimeoInfo;
import com.github.axet.vget.vhs.YoutubeInfo;
import com.github.axet.wget.info.DownloadInfo;
import com.github.axet.wget.info.DownloadInfo.Part.States;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import youtube.utils.UYoutube;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Yaroslav Zabara <yaroslav.zabara@waveaccess.ru>
 */
public class CYoutubeDownloader {

    private static Logger LOGGER = LoggerFactory.getLogger(CYoutubeDownloader.class);
    private String youtubeId;
    private File pathToDownload;
    private VideoInfo info;
    private long last;

    public CYoutubeDownloader(String ytPath, File pathToDownload) {
        setPathToDownload(pathToDownload);
        setYoutubeId(ytPath);
    }

    public String download() {
        try {
            AtomicBoolean stop = new AtomicBoolean(false);
            Runnable notify = new Runnable() {
                public void run() {
                    VideoInfo i1 = info;
                    DownloadInfo i2 = i1.getInfo();
                    // notify app or save download state
                    // you can extract information from DownloadInfo info;
                    switch (i1.getState()) {
                        case EXTRACTING:
                        case EXTRACTING_DONE:
                        case DONE:
                            if (i1 instanceof YoutubeInfo) {
                                YoutubeInfo i = (YoutubeInfo) i1;
                                LOGGER.debug(i1.getState() + " " + i.getVideoQuality());
                            } else if (i1 instanceof VimeoInfo) {
                                VimeoInfo i = (VimeoInfo) i1;
                                LOGGER.debug(i1.getState() + " " + i.getVideoQuality());
                            } else {
                                LOGGER.error("downloading unknown quality");
                            }
                            break;
                        case RETRYING:
                            LOGGER.info(i1.getState() + " " + i1.getDelay());
                            break;
                        case DOWNLOADING:
                            long now = System.currentTimeMillis();
                            if (now - 1000 > last) {
                                last = now;
                                String parts = "";
                                List<DownloadInfo.Part> pp = i2.getParts();
                                if (pp != null) {
                                    // multipart download
                                    for (DownloadInfo.Part p : pp) {
                                        if (p.getState().equals(States.DOWNLOADING)) {
                                            parts += String.format("Part#%d(%.2f) ", p.getNumber(), p.getCount()
                                                    / (float) p.getLength());
                                        }
                                    }
                                }
                                LOGGER.debug(String.format("%s %.2f %s", i1.getState(),
                                        i2.getCount() / (float) i2.getLength(), parts));
                            }
                            break;
                        default:
                            break;
                    }
                }
            };
            URL web = new URL(UYoutube.makeUrl(youtubeId));
            // [OPTIONAL] limit maximum quality, or do not call this function if
            // you wish maximum quality available.
            //
            // if youtube does not have video with requested quality, program
            // will raise en exception.
            VGetParser user = null;

            // create proper html parser depends on url
            user = VGet.parser(web);
            // download maximum video quality from youtube
            // user = new YouTubeQParser(YoutubeQuality.p480);
            //
            // download mp4 format only, fail if non exist
            // user = new YouTubeMPGParser();
            //
            // create proper videoinfo to keep specific video information
            info = user.info(web);
            VGet v = new VGet(info, pathToDownload);
            // [OPTIONAL] call v.extract() only if you d like to get video title
            // or download url link
            // before start download. or just skip it.
            v.extract(user, stop, notify);
            LOGGER.debug("Title: " + info.getTitle());
            LOGGER.debug("Download URL: " + info.getInfo().getSource());
            v.download(user, stop, notify);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return info.getTitle() + FilenameUtils.EXTENSION_SEPARATOR_STR + EVideoFormat.getFormatByMimeType(info.getInfo().getContentType()).getFileExtension();
    }

    private void setYoutubeId(String youtubeId) {
        String id = UYoutube.getYoutubeId(youtubeId);
        if (StringUtils.isBlank(id)) {
            throw new RuntimeException("Youtube id/url  is empty");
        }
        this.youtubeId = id;
    }

    private void setPathToDownload(File pathToDownload) {
        if (pathToDownload == null || pathToDownload.isFile() || !pathToDownload.exists()) {
            throw new RuntimeException("Path to download is incorrect(null or it's file or it isn't exist)");
        }
        this.pathToDownload = pathToDownload;
    }
}
