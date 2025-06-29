package com.yulcom.inoutfolderapp.application.folders.create.services;

import com.yulcom.inoutfolderapp.domain.entities.Folder;
import com.yulcom.inoutfolderapp.domain.entities.File;
import java.util.List;

public interface FileGenerationStrategy {
    List<File> generateFiles(Folder folder);
    boolean shouldExecute(Folder folder);
}
