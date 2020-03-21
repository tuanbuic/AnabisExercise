package vn.anibis.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class PropertiesUtil {
    private static PropertiesUtil PropsUtil;

    public PropertiesUtil() {
    }

    public static Properties load(String fileName) {
        Properties r = new Properties();
        FileInputStream isMessage = null;

        try {
            isMessage = new FileInputStream(fileName);
            r.load(new InputStreamReader(isMessage, Charset.forName("UTF-8")));
        } catch (Exception var12) {
            var12.printStackTrace();
        } finally {
            if (isMessage != null) {
                try {
                    isMessage.close();
                } catch (Exception var11) {

                }
            }

        }

        return r;
    }
}
