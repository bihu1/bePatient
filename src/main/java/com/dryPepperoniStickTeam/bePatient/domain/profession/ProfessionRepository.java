package com.dryPepperoniStickTeam.bePatient.domain.profession;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionRepository extends CrudRepository<Profession, Long> {
    List<Profession> findAll();
    List<Profession> findByIdIn(List<Long> idList);
}
