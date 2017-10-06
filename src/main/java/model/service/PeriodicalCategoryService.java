package model.service;

import java.util.*;

import model.entity.periodical.PeriodicalCategory;

public interface PeriodicalCategoryService {
	boolean addCategory(PeriodicalCategory category);
	List<PeriodicalCategory> findAll();
	Optional<PeriodicalCategory> findOneById(long id);
}
