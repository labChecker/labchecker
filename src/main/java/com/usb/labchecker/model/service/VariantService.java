package com.usb.labchecker.model.service;

import com.usb.labchecker.model.entity.Variant;
import com.usb.labchecker.model.repository.VariantRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class VariantService {
    private final VariantRepository variantRepository;

    public VariantService(VariantRepository variantRepository) {
        this.variantRepository = variantRepository;
    }

    public Variant getOne(int id) {
        return variantRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Iterable<Variant> getAllVariants() {
        return variantRepository.findAll();
    }
}
