package kr.co.study.delivery.application;

import kr.co.study.delivery.domain.Reservation;
import kr.co.study.delivery.domain.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class ReservationServiceTests {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;


    @BeforeEach
    public void setUp(){

        MockitoAnnotations.openMocks(this);

        reservationService = new ReservationService(reservationRepository);
    }

    @Test
    public void getReservations(){

        Long restaurantId = 1004L;

        List<Reservation> reservations = reservationService.getReservations(restaurantId);

        verify(reservationRepository).findAllByRestaurantId(restaurantId);
    }

}