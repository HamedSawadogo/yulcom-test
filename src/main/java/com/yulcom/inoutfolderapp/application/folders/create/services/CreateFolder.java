package com.yulcom.inoutfolderapp.application.folders.create.services;

import com.yulcom.inoutfolderapp.application.folders.create.dtos.in.CreateFolderRequest;
import com.yulcom.inoutfolderapp.application.folders.create.dtos.out.GetFolderResponse;
import com.yulcom.inoutfolderapp.application.folders.create.mappers.FolderMapper;
import com.yulcom.inoutfolderapp.commons.mappers.ProductMapper;
import com.yulcom.inoutfolderapp.domain.repositories.CorporateUserRepository;
import com.yulcom.inoutfolderapp.domain.repositories.CorporationRepository;
import com.yulcom.inoutfolderapp.domain.repositories.FileActionRepository;
import com.yulcom.inoutfolderapp.domain.repositories.FileRepository;
import com.yulcom.inoutfolderapp.domain.repositories.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateFolder
{
    private final CorporationRepository corporationRepository;
    private final CorporateUserRepository corporateUserRepository;
    private final FileActionRepository fileActionRepository;
    private final FolderRepository folderRepository;
    private final FileRepository fileRepository;
    private final FilesDeterminator filesDeterminator;


    public GetFolderResponse handle(CreateFolderRequest request) {
        var folder = FolderMapper.toEntity(request).build();
        folder.setProducts(ProductMapper.toEntityList(request.products()));
        var generatedFiles = filesDeterminator.handle(folder);
        folderRepository.save(folder);
        fileRepository.saveAll(generatedFiles);
        return FolderMapper.toResponse(folder);
    }
}
