package com.yulcom.inoutfolderapp.commons.aspects;

import com.yulcom.inoutfolderapp.domain.repositories.FolderRepository;
import com.yulcom.inoutfolderapp.infrastructure.configs.security.services.AuthenticatedUSerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class ProcessableFoldersOnlyAspect {

    private final FolderRepository folderRepository;
    private final AuthenticatedUSerService authenticateService;

    @Around("@annotation(com.yulcom.inoutfolderapp.commons.anotations.ProcessableFoldersOnly)")
    public Object filterProcessableFolders(ProceedingJoinPoint joinPoint) throws Throwable {
        return false;
    }
}
