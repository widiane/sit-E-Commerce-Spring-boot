package com.ecommerce.ecommerce.utils;

import java.util.Base64;

public class ImageUtil {
    public String getImgData(byte[] byteData) {
        return Base64.getMimeEncoder().encodeToString(byteData);
    }
}
