package vn.anibis.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.log4j.Logger;

public class FilesUtil {
    private static final Logger LOGGER = Logger.getLogger(FilesUtil.class);
    public static List<File> getListFileInFolder(String folderPath, String regex) {
        List<File> lstFiles = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(Paths.get(folderPath))) {
            lstFiles = walk.filter(p -> p.getFileName().toString().matches(regex)).map(Path::toFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lstFiles;
    }
    public static String getTextContent(String filePath){
        String result="";
        try{
            if(filePath!=null){
                File f = new File(filePath);
                if(f.exists()&& !f.isDirectory()){
                    result = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
                }else{
                    LOGGER.error("File not exist"+ filePath);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
