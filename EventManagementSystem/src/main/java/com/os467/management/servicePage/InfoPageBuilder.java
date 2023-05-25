package com.os467.management.servicePage;

import javax.swing.*;

public interface InfoPageBuilder {
    void buildPage(JPanel page);

    void setFileService(FileService fileService);

    void reBuildPage();
}
