package com.yulcom.inoutfolderapp.application.folders.create.controllers;

import com.yulcom.inoutfolderapp.application.folders.create.dtos.in.CreateFolderRequest;
import com.yulcom.inoutfolderapp.application.folders.create.dtos.out.GetFolderResponse;
import com.yulcom.inoutfolderapp.application.folders.create.services.CreateFolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateFolderController
{
    private final CreateFolder createFolder;

    @PostMapping("/folders")
    public ResponseEntity<GetFolderResponse> createFolder(@Validated @RequestBody CreateFolderRequest request)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(createFolder.handle(request));
    }
}
