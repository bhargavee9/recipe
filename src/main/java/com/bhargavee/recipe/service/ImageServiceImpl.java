package com.bhargavee.recipe.service;

import com.bhargavee.recipe.model.Recipe;
import com.bhargavee.recipe.repository.RecipeRepository;
import javassist.bytecode.ByteArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    RecipeRepository reciperepo;

    @Autowired
    public ImageServiceImpl(RecipeRepository reciperepo) {
        this.reciperepo = reciperepo;
    }

    @Override
    @Transactional
    public void saveImageFile(Long id, MultipartFile file)  {
           Recipe recipe = reciperepo.findById( id ).get();
           try {
               Byte[] byteobject = new Byte[file.getBytes().length];
               int i = 0;
               for (byte b : file.getBytes()) {
                   byteobject[i++] = b;
               }
               recipe.setImage( byteobject );
               reciperepo.save( recipe );
           }
           catch(IOException ioexception){

           }
    }
}
