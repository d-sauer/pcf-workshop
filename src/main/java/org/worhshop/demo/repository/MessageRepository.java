package org.worhshop.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.worhshop.demo.jpa.MyMessage;

public interface MessageRepository extends CrudRepository<MyMessage, Long> {
}
