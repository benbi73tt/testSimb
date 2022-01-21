package ru.simbirsoft.Prictice.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.simbirsoft.Prictice.page.WebPage;

@Repository
public interface WebPageRepository extends CrudRepository<WebPage, Integer> {
}
