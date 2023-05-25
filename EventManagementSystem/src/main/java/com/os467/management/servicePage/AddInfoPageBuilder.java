package com.os467.management.servicePage;

import javax.swing.*;

public interface AddInfoPageBuilder {
    void buildPage(JPanel page);
    void setFileService(FileService fileService);
    void setInfoPageBuilder(InfoPageBuilder infoPageBuilder);
}
