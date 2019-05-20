package com.dryPepperoniStickTeam.bePatient.domain.disease;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@DynamicUpdate //todo check if it works
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @CsvBindByPosition(position = 0)
    String name;
}
