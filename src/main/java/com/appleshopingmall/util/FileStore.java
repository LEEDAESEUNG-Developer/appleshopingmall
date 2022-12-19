package com.appleshopingmall.util;

import com.appleshopingmall.repository.AdminProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileStore {

    private final AdminProductRepository repository;

    @Value("${file.dir}")
    private String fileDir;

    // 반환 타입은 미정
    public Long storeFile(String filename, MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()) return null;

        filename = (fileDir + filename);

        File saveFile = new File(filename);

        //디렉토리가 없을 경우 생성
        if(!saveFile.exists()){
            try {
                String splitString = filename.substring(0, saveFile.toString().lastIndexOf("/"));
                log.info("경로 자른 후 => {}", splitString);
                File mkdir = new File(splitString);
                mkdir.mkdir();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
            log.info("파일 생성 완료");
        }


        multipartFile.transferTo(saveFile);
        return 0L;
    }
}
