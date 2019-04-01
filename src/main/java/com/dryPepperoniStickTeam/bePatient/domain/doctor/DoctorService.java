package com.dryPepperoniStickTeam.bePatient.domain.doctor;

import com.dryPepperoniStickTeam.bePatient.domain.doctor.http.model.DoctorDetails;
import com.dryPepperoniStickTeam.bePatient.domain.doctor.http.model.DoctorView;
import com.dryPepperoniStickTeam.bePatient.domain.service.MedicalService;
import com.dryPepperoniStickTeam.bePatient.domain.service.MedicalServiceRepository;
import com.dryPepperoniStickTeam.bePatient.domain.visit.VisitRepository;
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
    private final MedicalServiceRepository medicalServiceRepository;
    private final MapperFacade mapper;

    public List<DoctorView> getAllDoctors(){
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorView> doctorViews = mapper.mapAsList(doctors, DoctorView.class);
        //fixme change dummyContent to real content
        doctorViews.forEach(dv -> dv.setServices(asList("dummyContent")));
        return doctorViews;
    }

    public void addDoctor(DoctorDetails doctorDetails){
        Doctor doctor = mapper.map(doctorDetails, Doctor.class);
        List<MedicalService> medicalServices = medicalServiceRepository.findByIdIn(doctorDetails.getServices());
        if(medicalServices.size() != doctorDetails.getServices().size()){
            throw new RuntimeException();
        }
        doctor.setMedicalServices(medicalServices);
        doctorRepository.save(doctor);
    }
}
