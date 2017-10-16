package com.swen.herebethetitle.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javafx.scene.image.Image;

public final class Resources {
    private Resources() {
    }
    
    /**
     * Gets an image by name.
     * @param filename
     * @return
     */
    public static Image getImage(String filename) {
        InputStream is = get(filename);
        return new Image(is);
    }
    
    /**
     * Gets a resource as a string.
     */
    public static String getAsString(String filename) {
        InputStream is = get(filename);
        return new BufferedReader(new InputStreamReader(is))
            .lines().collect(Collectors.joining("\n"));
    }
    
    /**
     * Gets a resource by name.
     */
    public static InputStream get(String filename) {
        Object o = new Object();
        InputStream resourceStream = o.getClass().getResourceAsStream("res/" + filename);
        
        if (resourceStream != null)
            return resourceStream;
        
        // Otherwise attempt to load it via file.
        File file = new File("res/" + filename);
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("unknown resource: '" + filename + "'");
        }
    }
}
