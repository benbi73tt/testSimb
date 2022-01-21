package ru.simbirsoft.Practice.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.simbirsoft.Practice.page.WebPage;

@Repository
public interface WebPageRepository extends CrudRepository<WebPage, Integer> {
}
