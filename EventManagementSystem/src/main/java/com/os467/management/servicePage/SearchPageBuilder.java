package com.os467.management.servicePage;

import javax.swing.*;

public interface SearchPageBuilder {
    void buildPage(JPanel page);

    void setFileService(FileService fileService);
}
