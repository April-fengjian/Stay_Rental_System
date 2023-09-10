package com.laioffer.staybooking.repository;

import com.laioffer.staybooking.model.Location;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

//最后会由spring boot来实现，所以都定义为 interface
@Repository
public interface LocationRepository extends ElasticsearchRepository<Location, Long>, CustomLocationRepository {

}

