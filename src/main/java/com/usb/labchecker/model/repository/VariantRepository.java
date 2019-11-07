package com.usb.labchecker.model.repository;

import com.usb.labchecker.model.entity.Variant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantRepository extends CrudRepository<Variant, Integer> {
}
