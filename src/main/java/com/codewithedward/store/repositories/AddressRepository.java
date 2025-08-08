package com.codewithedward.store.repositories;

import com.codewithedward.store.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address,Long> {
}
