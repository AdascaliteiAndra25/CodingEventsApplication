package com.launchcode.hellor_spring.data;

import com.launchcode.hellor_spring.models.Event;
import com.launchcode.hellor_spring.models.EventCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

}
