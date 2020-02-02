package net.devdiaries.reports.repository;

import net.devdiaries.reports.Offer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface OffersRepository extends CrudRepository<Offer, Long>, JpaSpecificationExecutor<Offer> {

    List<Offer> findAllByCity(String city);
}