package vn.anibis.core.repository;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import vn.anibis.core.config.Configuration;
import vn.anibis.core.enums.LocatorType;
import vn.anibis.util.FilesUtil;
import vn.anibis.util.PropertiesUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ObjectRepository {
    static final Logger logger = Logger.getLogger(ObjectRepository.class.getName());
    private static ObjectRepository objectRepository;
    static final String REG_EX = ".*.properties";
    static final String SEPARATE_KEY = ":::";
    Properties propObjRepo = null;

    public static ObjectRepository instance() {
        if (objectRepository == null) {
            objectRepository = new ObjectRepository();
            objectRepository.loadObjectRepositories();
        }
        return objectRepository;
    }

    private void loadObjectRepositories() {
        try {
            propObjRepo = new Properties();
            List<File> objRepo = new ArrayList<>();
            //Properties files from folder
            List<File> objRepoInFolder = FilesUtil.getListFileInFolder(Configuration.instance().getValue(Configuration.OBJ_REPO_FOLDER), REG_EX);

            if (objRepoInFolder != null) {
                objRepo.addAll(objRepoInFolder);
            }

            for (int i = 0; i < objRepo.size(); i++) {
                Properties p = PropertiesUtil.load(objRepo.get(i).getAbsolutePath());
                propObjRepo.putAll(p);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }



    public By getBy(String objName) {
        By r = null;
        try {
            LocatorType locatorType = LocatorType.XPATH;
            String locatorValue;
            if (objName.startsWith("//") || objName.startsWith("./") || objName.startsWith("/") ||
                    objName.startsWith("(/")) {
                locatorValue = objName;
            } else{
                locatorValue = propObjRepo.getProperty(objName);
                int index = locatorValue.indexOf(SEPARATE_KEY);
                if (index > 0) {
                    locatorType = getLocatorType(locatorValue);
                    locatorValue = getLocatorValue(locatorValue);
                }
            }
            switch (locatorType) {
                case ID:
                    r = By.id(locatorValue);
                    break;
                case NAME:
                    r = By.name(locatorValue);
                    break;
                case CSSSELECTOR:
                    r = By.cssSelector(locatorValue);
                    break;
                case XPATH:
                    r = By.xpath(locatorValue);
                    break;
                default:
                    logger.info("No locator Type found with" + locatorType);
                    break;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return r;
    }

    private String getLocatorValue(String objPath) {
        int index = objPath.indexOf(SEPARATE_KEY);
        if (index > 0) {
            return objPath.substring(index + SEPARATE_KEY.length()).trim();
        }
        return "";
    }

    private LocatorType getLocatorType(String objPath) {
        LocatorType locatorType = LocatorType.XPATH;
        int index = objPath.indexOf(SEPARATE_KEY);
        if (index > 0) {
            locatorType = LocatorType.valueOf(objPath.substring(0, index).trim().toUpperCase());
        }
        return locatorType;
    }

    public Properties getObjectRepository() {
        return this.propObjRepo;
    }
}
