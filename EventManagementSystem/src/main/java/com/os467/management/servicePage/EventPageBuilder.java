package com.os467.management.servicePage;

import javax.swing.*;

public interface EventPageBuilder {
    void buildPage(JPanel page);
    void setFileService(FileService fileService);
}
