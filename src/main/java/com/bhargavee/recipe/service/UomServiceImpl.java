package com.bhargavee.recipe.service;

import com.bhargavee.recipe.model.UnitOfMeasure;
import com.bhargavee.recipe.repository.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UomServiceImpl implements UomService {
    UnitOfMeasureRepository uomrepo;
    @Autowired
    public UomServiceImpl(UnitOfMeasureRepository uomrepo) {
        this.uomrepo = uomrepo;
    }

    @Override
    public Set<UnitOfMeasure> getAllUoms() {
        Set<UnitOfMeasure> alluoms = new HashSet<>(  );
        uomrepo.findAll().iterator().forEachRemaining( alluoms::add );
        return alluoms;
    }
}
