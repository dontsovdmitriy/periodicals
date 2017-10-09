package model.service;

import java.util.*;

import model.entity.periodical.PeriodicalCategory;

/**
 * The {@code PeriodicalCategoryService} interface provides methods for {@code PeriodicalCategory} object.
 */
public interface PeriodicalCategoryService {
	
	/**
     * Method add category
     * @param category periodical category
     * @return true if the addition is successful
     */
	boolean addCategory(PeriodicalCategory category);
	
	/**
     * Method find all periodical categories
     * @return List of periodical categories
     */
	List<PeriodicalCategory> findAll();
	
	/**
     * Method find periodical category by id
     * @param id
     * @return Optional<PeriodicalCategory>
     */
	Optional<PeriodicalCategory> findOneById(long id);
}
