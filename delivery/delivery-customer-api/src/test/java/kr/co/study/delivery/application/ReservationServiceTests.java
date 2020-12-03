package kr.co.study.delivery.application;

import kr.co.study.delivery.domain.Reservation;
import kr.co.study.delivery.domain.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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
    public void addReservation(){

        Long userId = 1004L;
        Long restaurantId = 369L;
        String name = "John";
        String date = "2020-12-24";
        String time = "20:00";
        Integer partySize = 20;

        Reservation mockReservation = Reservation.builder()
                .name(name)
                .build();

        // 어떤 걸 실행(invoke) 할건지 바로 정해줌. 우리가 받은 첫번쨰 인자
        given(reservationRepository.save(any())).will(invocation -> {
            Reservation reservation = invocation.getArgument(0);
            return reservation;
        });

        Reservation reservation = reservationService.addReservation(
                restaurantId, userId, name, date, time, partySize);

        assertThat(reservation.getName()).isEqualTo(name);

        verify(reservationRepository).save(any(Reservation.class));
    }

}