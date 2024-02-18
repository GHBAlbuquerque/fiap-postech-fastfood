package com.fiap.fastfood.adapters.persistence;

import com.fiap.fastfood.adapters.persistence.orm.ClientORM;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataMongoClientRepository extends MongoRepository<ClientORM, String> {

    ClientORM findByCpf(String cpf);
}
