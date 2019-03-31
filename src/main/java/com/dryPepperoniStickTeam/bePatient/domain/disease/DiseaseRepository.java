package com.dryPepperoniStickTeam.bePatient.domain.disease;

import com.dryPepperoniStickTeam.bePatient.domain.service.MedicalService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseRepository extends CrudRepository<Disease, Long> {
    List<Disease> findAll();
}
