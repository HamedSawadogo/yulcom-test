package com.yulcom.inoutfolderapp.application.folders.getall.controllers;

import com.yulcom.inoutfolderapp.application.folders.getall.dtos.in.GetFoldersRequest;
import com.yulcom.inoutfolderapp.application.folders.getall.dtos.out.GetFoldersResponseDto;
import com.yulcom.inoutfolderapp.application.folders.getall.services.GetFolders;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetFoldersController
{
    private final GetFolders  getFolders;

    @GetMapping("/folders")
    public ResponseEntity<GetFoldersResponseDto> getFolders(
        @RequestParam("page") int page,
        @RequestParam("size") int size
    )
    {
        return ResponseEntity.ok().body(getFolders.handle(new GetFoldersRequest(page, size)));
    }
}
