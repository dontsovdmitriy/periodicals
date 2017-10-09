package model.service;

import java.util.List;
import java.util.Optional;

import model.entity.periodical.Periodical;

/**
 * The {@code PeriodicalService} interface provides methods for {@code Periodical} object.
 */
public interface PeriodicalService {

	/**
     * Method add periodical
     * @param periodical periodical
     * @return true if the addition is successful
     */
	boolean addPeriodical(Periodical periodical);
	
	/**
     * Method edit periodical
     * @param periodical periodical
     * @return true if the editing is successful
     */
	boolean editPeriodical(Periodical periodical);
	
	/**
     * Method find all periodical 
     * @return List of periodical 
     */
	List<Periodical> findAll();
	
	/**
     * Method find periodical by id
     * @param id
     * @return Optional<Periodical>
     */
	Optional<Periodical> findOneById(long id);
}
