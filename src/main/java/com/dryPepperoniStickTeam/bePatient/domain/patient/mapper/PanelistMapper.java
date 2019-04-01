package com.dryPepperoniStickTeam.bePatient.domain.patient.mapper;

import com.dryPepperoniStickTeam.bePatient.config.orika.Mapping;
import com.dryPepperoniStickTeam.bePatient.config.orika.OrikaBeanMapping;
import com.dryPepperoniStickTeam.bePatient.domain.patient.http.model.PatientDetails;
import com.dryPepperoniStickTeam.bePatient.domain.patient.model.Patient;
import ma.glasnost.orika.MapperFactory;

@Mapping
public class PanelistMapper implements OrikaBeanMapping {
    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(PatientDetails.class, Patient.class)
                .field("pesel","username")
                .byDefault()
                .register();
    }
}
