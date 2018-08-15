package com.bhargavee.recipe.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public interface ImageService {

    public void saveImageFile(Long id, MultipartFile file);
}
