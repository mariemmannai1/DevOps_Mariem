package tn.esprit.eventsproject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.repositories.LogisticsRepository;

import static org.mockito.Mockito.*;

@SpringBootTest
class LogisticsTest {

    @Mock
    private LogisticsRepository logisticsRepository;

    @InjectMocks
    private Logistics logistics;

    @Test
    void testLogisticsCreation() {
        // Given
        logistics.setDescription("Sample description");
        logistics.setReserve(true);
        logistics.setPrixUnit(10.0f);
        logistics.setQuantite(5);

        when(logisticsRepository.save(any(Logistics.class))).thenReturn(logistics);

        // When
        Logistics savedLogistics = logisticsRepository.save(logistics);

        // Then
        assertNotNull(savedLogistics);
        assertEquals("Sample description", savedLogistics.getDescription());
        assertTrue(savedLogistics.isReserve());
        assertEquals(10.0f, savedLogistics.getPrixUnit(), 0.001);
        assertEquals(5, savedLogistics.getQuantite());
    }
}
