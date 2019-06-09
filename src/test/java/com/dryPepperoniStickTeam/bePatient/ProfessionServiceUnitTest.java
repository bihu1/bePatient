package com.dryPepperoniStickTeam.bePatient;

import com.dryPepperoniStickTeam.bePatient.domain.profession.Profession;
import com.dryPepperoniStickTeam.bePatient.domain.profession.ProfessionRepository;
import com.dryPepperoniStickTeam.bePatient.domain.profession.ProfessionService;
import com.dryPepperoniStickTeam.bePatient.domain.profession.http.model.ProfessionDetails;
import com.dryPepperoniStickTeam.bePatient.domain.profession.http.model.ProfessionUpdate;
import com.dryPepperoniStickTeam.bePatient.domain.profession.http.model.ProfessionView;
import ma.glasnost.orika.MapperFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProfessionServiceUnitTest {

    @Mock
    ProfessionRepository professionRepository;

    @Mock
    MapperFacade mapper;

    @InjectMocks
    ProfessionService professionService;

    @Test
    public void shouldSaveProfession() {
        ProfessionDetails professionDetails = new ProfessionDetails();
        Profession profession = new Profession();

        when(mapper.map(professionDetails, Profession.class)).thenReturn(profession);
        professionService.addProfession(professionDetails);

        verify(professionRepository).save(profession);
    }

    @Test
    public void shouldUpdateProfession() {
        long id = 5;
        ProfessionUpdate professionUpdate = new ProfessionUpdate();
        Profession profession = new Profession();

        when(mapper.map(professionUpdate, Profession.class)).thenReturn(profession);
        when(professionRepository.existsById(id)).thenReturn(true);
        professionService.updateProfession(id, professionUpdate);

        verify(professionRepository).save(profession);
        assertThat(profession.getId()).isEqualTo(id);
    }

    @Test
    public void shouldDeleteProfession() {
        long id = 5;

        when(professionRepository.existsById(id)).thenReturn(true);
        professionService.deleteProfession(id);

        verify(professionRepository).deleteById(id);
    }

    @Test
    public void shouldReturnProfession() {
        long id = 5;
        Profession profession = new Profession();

        when(professionRepository.findById(id)).thenReturn(Optional.of(profession));
        professionService.getProfession(id);

        verify(mapper).map(profession, ProfessionView.class);
    }

    @Test
    public void shouldReturnProfessions() {
        Profession profession = new Profession();
        Profession profession1 = new Profession();

        when(professionRepository.findAll()).thenReturn(asList(profession, profession1));
        professionService.getAllProfessions();

        verify(mapper).mapAsList(asList(profession, profession1), ProfessionView.class);
    }
}
