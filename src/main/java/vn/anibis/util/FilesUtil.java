package vn.anibis.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilesUtil {
    public static List<File> getListFileInFolder(String folderPath, String regex) {
        List<File> lstFiles = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(Paths.get(folderPath))) {
            lstFiles = walk.filter(p -> p.getFileName().toString().matches(regex)).map(Path::toFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lstFiles;
    }
}
