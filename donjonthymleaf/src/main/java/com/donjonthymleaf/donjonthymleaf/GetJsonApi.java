package com.donjonthymleaf.donjonthymleaf;

import org.springframework.web.client.RestTemplate;

public class GetJsonApi {
    static final String URL_EMPLOYEES = "http://localhost:8081/personnage";

    static final String URL_EMPLOYEES_XML = "http://localhost:8081/employees.xml";
    static final String URL_EMPLOYEES_JSON = "http://localhost:8081/employees.json";

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();

        // Send request with GET method and default Headers.
        Person[] list = restTemplate.getForObject(URL_EMPLOYEES, Person[].class);

        if (list != null) {
            for (Person e : list) {
                System.out.println("Personnage: " + e.getName() + " " + " type : " + e.getType());
            }
        }
    }

}
