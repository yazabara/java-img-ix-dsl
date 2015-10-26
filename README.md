# java-test

###ImgIx helpers

Contains classes to help with ImgIx(https://webapp.imgix.com/) services (using amazon S3);

CImgIxBuilder - for making url, using DSL like:

```
IImgIxBuilder builder = new CImgIxBuilder(imgIxDomain, "amazonImgPath");
        builder.setWidth("480")
                .setHeight("300")
                .setTrim("auto", "black", "9", "8", "")
                .setAuto("enhance")
                .setMonochrome("f00")
                .setExtension("jpg");
```
Where imgIxDomain - your imgIx domain. amazonImgPath - path to img in amazonS3 bucket.

As result - it will be link to new enhanced image:
```
builder.makeLink();
```

For downloading result(new image or palette json) you can use CImgIxDownloader:
```
public static BufferedImage downloadImage(String url) throws IOException
public static BufferedImage downloadImage(IImgIxBuilder builder) throws IOException 
public static CPaletteJson downloadPaletteJson(String url) throws IOException
public static CPaletteJson downloadPaletteJson(IImgIxBuilder builder) throws IOException
```
