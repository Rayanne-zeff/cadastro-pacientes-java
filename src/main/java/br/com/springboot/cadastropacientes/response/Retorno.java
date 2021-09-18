package br.com.springboot.cadastropacientes.response;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Gloria Rayane
 * @since : 17/09/2021
 */

@Getter
@Setter
public class Retorno {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj){
        Map<String, Object> map = new HashMap<String,Object>();
        map.put ("status", status.value());
        map.put("data", responseObj);
        map.put("message", message);

        return new ResponseEntity<Object>(map,status);
    }

}
