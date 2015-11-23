package youtube;

import org.apache.commons.beanutils.BeanPropertyValueEqualsPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public enum EVideoFormat implements IContentFormat {
    MP4("mp4", "video/mp4"), //
    MOV("mov", "video/quicktime"), //
    M4V("m4v", "video/x-m4v"), //
    F4V("f4v", "video/x-f4v"), //
    FLV("flv", "video/x-flv"), //
    AVI("avi", "video/x-msvideo"), //
    WMV("wmv", "video/x-ms-wmv"), //
    ;
    private String fileExtension;
    private String mimeType;

    private EVideoFormat(String fileExtension, String mimeType) {
        this.fileExtension = fileExtension;
        this.mimeType = mimeType;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public String getMimeType() {
        return mimeType;
    }

    public static boolean isVideo(String filename) {
        if (StringUtils.isBlank(filename)) {
            return false;
        }
        String extension = FilenameUtils.getExtension(filename);
        for (EVideoFormat format : values()) {
            if (format.fileExtension.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    public static EVideoFormat getFormatByMimeType(String mimeType) {
        return (EVideoFormat) CollectionUtils.find(Arrays.asList(EVideoFormat.values()), new BeanPropertyValueEqualsPredicate("mimeType", mimeType));
    }
}
