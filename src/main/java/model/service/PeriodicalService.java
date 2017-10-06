package model.service;

import java.util.List;
import java.util.Optional;

import model.entity.periodical.Periodical;

public interface PeriodicalService {

	boolean addPeriodical(Periodical periodical);
	boolean editPeriodical(Periodical periodical);
	List<Periodical> findAll();
	Optional<Periodical> findOneById(long id);
}
