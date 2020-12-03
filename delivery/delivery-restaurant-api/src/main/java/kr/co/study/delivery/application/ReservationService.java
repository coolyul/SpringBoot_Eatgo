package kr.co.study.delivery.application;

import kr.co.study.delivery.domain.Reservation;
import kr.co.study.delivery.domain.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReservationService {


    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getReservations(Long restaurantId) {

        return reservationRepository.findAllByRestaurantId(restaurantId);

    }
}
