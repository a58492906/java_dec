package org.reactive.curd;

import org.jboss.logging.Logger;
import org.reactive.model.City;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import java.util.*;

/**
 * @author xjm
 * @version 1.0
 * @date 2022-03-06 21:40
 */
@ApplicationScoped
public class CityService {

    private static final Logger LOGGER = Logger.getLogger(ReactiveGreetingService.class.getName());

    private final Map<String,String> map=new HashMap<>();

    @Inject
    CityMapper cityMapper;


    public String  getCode(String  name) {
        if (map.containsKey(name)){
            return map.get(name);
        }
        else{
            Optional<City> optional= cityMapper.findByName(name);
            if (!optional.isPresent()) {
                throw new WebApplicationException("org.reactive.model.City with name of " + name + " does not exist.", 404);
            }
            String code =optional.get().getCode();
            map.put(name,code);
            return code;
        }
    }
}
