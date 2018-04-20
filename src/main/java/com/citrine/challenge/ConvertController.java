package com.citrine.challenge;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController()
public class ConvertController {
    
	private Converter converter = new Converter();
	
    @RequestMapping("/units/si")
    public ResponseEntity<UnitInfo> siConvert(@RequestParam(value="units") String units) {
    	
    	try {
    		UnitInfo ui = converter.convert(units);
    		return new ResponseEntity<>(ui, HttpStatus.OK);
    	} catch (Throwable t) {
    		HttpHeaders headers = new HttpHeaders();
    		headers.add("message", t.getMessage());
    		return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    	}
    }
    
}
