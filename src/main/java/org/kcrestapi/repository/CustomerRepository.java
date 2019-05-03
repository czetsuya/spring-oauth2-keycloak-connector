package org.kcrestapi.repository;

import org.kcrestapi.model.CustomerModel;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerModel, Long> {
}
