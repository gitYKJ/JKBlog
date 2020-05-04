package com.jkblog.util;

import java.net.URL;

public class LocationURL {
    public static void main(String[] args) {
        URL location = LocationURL.class
                .getProtectionDomain()
                .getCodeSource().getLocation();
        System.out.println(location);
    }
}





