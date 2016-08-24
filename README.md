# java-test

###ImgIx helpers

Contains classes to help with [ImgIx](https://webapp.imgix.com/) service;

[Article about CImgIxBuilder](http://www.waveaccess.ru/blog/2016/june/29/%D0%BE%D0%B1%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%BA%D0%B0-%D0%B8%D0%B7%D0%BE%D0%B1%D1%80%D0%B0%D0%B6%D0%B5%D0%BD%D0%B8%D0%B9-%D1%81-%D0%BF%D0%BE%D0%BC%D0%BE%D1%89%D1%8C%D1%8E-imgix.aspx#.V71PLpiLSiN)

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
