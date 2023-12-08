package tn.esprit.eventsproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.LogisticsRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;
import tn.esprit.eventsproject.services.EventServicesImpl;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

 class EventTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private LogisticsRepository logisticsRepository;

    @InjectMocks
    private EventServicesImpl eventServices;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testAddParticipant() {
        Participant participant = new Participant();
        when(participantRepository.save(participant)).thenReturn(participant);

        Participant result = eventServices.addParticipant(participant);

        verify(participantRepository, times(1)).save(participant);
        assertNotNull(result);
    }

    @Test
     void testAddAffectEvenParticipantWithId() {
        Event event = new Event();
        Participant participant = Participant.builder().idPart(1).build();
        when(participantRepository.findById(1)).thenReturn(Optional.of(participant));
        when(eventRepository.save(event)).thenReturn(event);

        Event result = eventServices.addAffectEvenParticipant(event, 1);

        verify(eventRepository, times(1)).save(event);
        assertTrue(participant.getEvents().contains(event));
        assertNotNull(result);
    }


    @Test
    void testAddAffectEvenParticipantWithParticipantSet() {
        Event event = new Event();
        Participant participant = new Participant();
        Set<Participant> participants = new HashSet<>();
        participants.add(participant);
        event.setParticipants(participants);
        when(participantRepository.findById(participant.getIdPart())).thenReturn(Optional.of(participant));
        when(eventRepository.save(event)).thenReturn(event);

        Event result = eventServices.addAffectEvenParticipant(event);

        verify(eventRepository, times(1)).save(event);
        assertTrue(participant.getEvents().contains(event));
        assertNotNull(result);
    }

    @Test
     void testAddAffectLog() {
        Logistics logistics = new Logistics();
        Event event = new Event();
        event.setDescription("EventDescription");
        when(eventRepository.findByDescription("EventDescription")).thenReturn(event);
        when(logisticsRepository.save(logistics)).thenReturn(logistics);

        Logistics result = eventServices.addAffectLog(logistics, "EventDescription");

        verify(logisticsRepository, times(1)).save(logistics);
        assertTrue(event.getLogistics().contains(logistics));
        assertNotNull(result);
    }

    @Test
      void testGetLogisticsDates() {
        LocalDate dateDebut = LocalDate.of(2023, 1, 1);
        LocalDate dateFin = LocalDate.of(2023, 12, 31);
        Event event = new Event();
        Logistics logistics = new Logistics();
        logistics.setReserve(true);
        Set<Logistics> logisticsSet = new HashSet<>();
        logisticsSet.add(logistics);
        event.setLogistics(logisticsSet);
        when(eventRepository.findByDateDebutBetween(dateDebut, dateFin)).thenReturn(Collections.singletonList(event));

        List<Logistics> result = eventServices.getLogisticsDates(dateDebut, dateFin);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.get(0).isReserve());
    }

}
