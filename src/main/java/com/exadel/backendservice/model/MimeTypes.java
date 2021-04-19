package com.exadel.backendservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MimeTypes {

    PDF("application/pdf"),
    BMP("image/bmp"),
    GIF("image/gif"),
    JPEG("image/jpeg"),
    PNG("image/png"),
    DOC("application/msword"),
    DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document");

    @Getter
    public String mime;

    public static boolean isCvFile(String mime) {
        return mime.equals(PDF.getMime()) || mime.equals(DOC.getMime()) || mime.equals(DOCX.getMime());
    }

    public static boolean isImage(String mime) {
        return mime.equals(PNG.getMime()) || mime.equals(BMP.getMime()) || mime.equals(GIF.getMime()) || mime.equals(JPEG.getMime());
    }

}
