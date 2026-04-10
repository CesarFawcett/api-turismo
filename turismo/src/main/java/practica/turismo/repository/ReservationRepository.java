package practica.turismo.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import practica.turismo.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUserId(Long userId);

    @Query("SELECT r FROM Reservation r WHERE r.tour.id = :tourId AND r.date = :date")
    List<Reservation> findByTourAndDate(Long tourId, LocalDate date);
}
