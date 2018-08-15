package com.bhargavee.recipe.repository;

import com.bhargavee.recipe.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {
    java.util.Optional<UnitOfMeasure> findByDescription(String description);
}
