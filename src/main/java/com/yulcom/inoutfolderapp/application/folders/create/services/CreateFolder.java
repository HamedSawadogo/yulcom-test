package com.yulcom.inoutfolderapp.application.folders.create.services;

import com.yulcom.inoutfolderapp.application.folders.create.dtos.in.CreateFolderRequest;
import com.yulcom.inoutfolderapp.application.folders.create.dtos.out.GetFolderResponse;
import com.yulcom.inoutfolderapp.application.folders.create.mappers.FolderMapper;
import com.yulcom.inoutfolderapp.commons.mappers.ProductMapper;
import com.yulcom.inoutfolderapp.domain.repositories.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateFolder
{
    private final FolderRepository folderRepository;
    private final FilesDeterminator filesDeterminator;

    public GetFolderResponse handle(CreateFolderRequest request) {
        var folder = FolderMapper.toEntity(request).build();
        folder.setProducts(ProductMapper.toEntityList(request.products()));
        var generatedFiles = filesDeterminator.handle(folder);
        folder.setFiles(generatedFiles);
        folderRepository.save(folder);
        return FolderMapper.toResponse(folder);
    }
}
