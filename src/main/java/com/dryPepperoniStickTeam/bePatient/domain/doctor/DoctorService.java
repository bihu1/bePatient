package com.dryPepperoniStickTeam.bePatient.domain.doctor;

import com.dryPepperoniStickTeam.bePatient.domain.doctor.http.model.DoctorView;
import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final MapperFacade mapper;

    public List<DoctorView> getAllDoctors(){
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorView> doctorViews = mapper.mapAsList(doctors, DoctorView.class);
        //fixme change dummyContent to real content
        doctorViews.forEach(dv -> dv.setServices(asList("dummyContent")));
        return doctorViews;
    }
}
