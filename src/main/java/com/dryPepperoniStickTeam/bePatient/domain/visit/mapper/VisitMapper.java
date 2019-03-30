package com.dryPepperoniStickTeam.bePatient.domain.visit.mapper;

import com.dryPepperoniStickTeam.bePatient.config.orika.Mapping;
import com.dryPepperoniStickTeam.bePatient.config.orika.OrikaBeanMapping;
import com.dryPepperoniStickTeam.bePatient.domain.patient.http.model.PatientDetails;
import com.dryPepperoniStickTeam.bePatient.domain.patient.model.Patient;
import com.dryPepperoniStickTeam.bePatient.domain.visit.Visit;
import com.dryPepperoniStickTeam.bePatient.domain.visit.http.model.ReservedVisitView;
import ma.glasnost.orika.MapperFactory;

@Mapping
public class VisitMapper implements OrikaBeanMapping {
    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(Visit.class, ReservedVisitView.class)
                .field("doctor.firstName","doctorFirstName")
                .field("doctor.lastName","doctorLastName")
                .field("dateFrom","date")
                .byDefault()
                .register();
    }
}
