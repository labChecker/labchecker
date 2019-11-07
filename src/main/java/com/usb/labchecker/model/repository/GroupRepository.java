package com.usb.labchecker.model.repository;

import com.usb.labchecker.model.entity.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends CrudRepository<Group, Integer> {
    Optional<Group> findByName(String name);
}
