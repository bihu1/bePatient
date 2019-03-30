package com.dryPepperoniStickTeam.bePatient.domain.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalServiceRepository extends CrudRepository<MedicalService, Long> {
    List<MedicalService> findAll();
}
