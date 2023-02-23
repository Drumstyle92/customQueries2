package it.develhope.customQueries2.repositories;

import it.develhope.customQueries2.entities.FlightEntity;
import it.develhope.customQueries2.entities.FlightStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Drumstyle92
 * Repository interface to automate the relationship between entities and controllers
 */
@Repository
public interface FlightRepository extends JpaRepository<FlightEntity,Long> {

    /**
     * @param status Parameter taken from the controller
     * @param of Parameter taken from the controller
     * @return Return data from the controller
     * Method taken from the controller
     */
    Page<FlightEntity> findAllByStatus(FlightStatus status, PageRequest of);

    /**
     * @return Return data from the controller
     * Method taken from the controller with a custom query
     */
    @Query("SELECT f FROM FlightEntity f WHERE f.status = 'DELAYED' OR f.status = 'CANCELLED'")
    List<FlightEntity> getCustomStatusFlight();
}
