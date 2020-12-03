package kr.co.study.delivery.application;

import kr.co.study.delivery.domain.Reservation;
import kr.co.study.delivery.domain.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReservationService {


    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation addReservation(
            Long restaurantId, Long userId, String name, String date, String time, Integer partySize) {

        Reservation reservation = Reservation.builder()
                .restaurantId(restaurantId)
                .userId(userId)
                .name(name)
                .date(date)
                .time(time)
                .partySize(partySize)
                .build();

        return reservationRepository.save(reservation);

    }
}
