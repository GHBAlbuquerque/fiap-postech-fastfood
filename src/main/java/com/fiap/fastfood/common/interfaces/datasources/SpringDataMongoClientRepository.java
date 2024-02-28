package com.fiap.fastfood.common.interfaces.datasources;

import com.fiap.fastfood.external.orm.ClientORM;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataMongoClientRepository extends MongoRepository<ClientORM, String> {

    ClientORM findByCpf(String cpf);
}
