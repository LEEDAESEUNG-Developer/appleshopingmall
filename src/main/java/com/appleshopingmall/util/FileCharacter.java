package com.appleshopingmall.util;

public class FileCharacter {
    private static final FileCharacter instance = new FileCharacter();

    private FileCharacter(){}

    public static FileCharacter getInstance() {
        return instance;
    }

    public static String stringAdditionSlash(String character){
        return character + "/";
    }

    public static String stringAdditionMinus(String character) {
        return character + "-";
    }

    public static String extractExt(String originalFilename){
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos);
    }
}
