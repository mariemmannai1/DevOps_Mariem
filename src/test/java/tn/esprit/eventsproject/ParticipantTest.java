package tn.esprit.eventsproject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.entities.Tache;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

@SpringBootTest
class ParticipantTest {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private Participant participant;

    @Test
    void testAddEvent() {
// Given
        // Création d'un objet Participant fictif
        Participant participant = new Participant();
        participant.setIdPart(1);
        participant.setNom("John");
        participant.setPrenom("Doe");
        participant.setTache(Tache.ORGANISATEUR);

        // Création d'un objet Event fictif
        Event event = new Event();
        event.setIdEvent(1);
        event.setDescription("Événement de test");
        event.setDateDebut(LocalDate.now());
        event.setDateFin(LocalDate.now().plusDays(3));
        event.setCout(0f);

        // Simulation du comportement du repository
        when(participantRepository.save(any(Participant.class))).thenReturn(participant);

        // When
        Set<Event> events = new HashSet<>();
        events.add(event);
        participant.setEvents(events);

        // Then
        assertNotNull(participant.getEvents());
        assertTrue(participant.getEvents().contains(event));
    }
}

