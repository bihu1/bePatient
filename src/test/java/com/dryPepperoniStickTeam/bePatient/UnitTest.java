package com.dryPepperoniStickTeam.bePatient;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class UnitTest {

    @Test
    public void exampleTest() {
        int x = 3;
        assertThat(x).isEqualTo(3);
    }
}
