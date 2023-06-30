package com.donjonthymleaf.donjonthymleaf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {

    private static List<Person> persons = new ArrayList<Person>();
    private int idCount;


    // Injectez (inject) via application.properties.
    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", message);

        return "index";
    }

//    @RequestMapping(value = {"/personList"}, method = RequestMethod.GET)
//    public String personList(Model model) {
//
//        model.addAttribute("persons", persons);
//
//        return "personList";
//    }

    @RequestMapping(value = {"/personList"}, method = RequestMethod.GET)
    public String personList(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8081/personnage";
        Person[] response = restTemplate.getForObject(fooResourceUrl, Person[].class);
        model.addAttribute("response", response);
        return "personList";
    }

    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {

        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);

        return "addPerson";
    }

//    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.POST)
//    public String savePerson(Model model, //
//                             @ModelAttribute("personForm") PersonForm personForm) {
//
//        String name = personForm.getName();
//        PersonType type = personForm.getType();
//
//        if (name != null && name.length() > 0) {
//            Person newPerson = new Person(this.idCount, name, type);
//            persons.add(newPerson);
//            this.idCount++;
//            return "redirect:/personList";
//        }
//
//        model.addAttribute("errorMessage", errorMessage);
//        return "addPerson";
//    }

    @PostMapping(value = {"/addPerson"})
    public String savePerson(
            @ModelAttribute PersonForm personform) {
        String name = personform.getName();
        String type = String.valueOf(personform.getType());


        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/personnage";


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        PersonForm personForm = new PersonForm(name, type);
        HttpEntity<PersonForm> request = new HttpEntity<>(personForm, headers);

        restTemplate.postForObject(url, request, Person.class);

        return "redirect:/personList";
    }

    @RequestMapping(value = "/deletePerson/{id}", method = RequestMethod.GET)
    public String deletePerson(@PathVariable("id") int id) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/personnage/" + id;
        restTemplate.delete(url);

        return "redirect:/personList";
    }

    @GetMapping(value = "/updatePerson/{id}")
    public String getPersonInfo(@PathVariable("id") int id, Model model) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/personnage/" + id;

        Person person = restTemplate.getForObject(url, Person.class, id);
        if (person != null) {
            PersonForm personForm = new PersonForm();

            personForm.setName(person.getName());
            personForm.setType(person.getType());

            model.addAttribute("personForm", personForm);
            model.addAttribute("person", person);
            return "updatePerson";
        }
        return "redirect:/personList";
    }

    @PutMapping(value = "updatePerson/{id}")
    public String updatePerson(@PathVariable("id") int id,
                               @ModelAttribute("personForm") PersonForm personForm) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/personnage/" + id;

        PersonForm personForme = new PersonForm(personForm.getName(), personForm.getType());
        HttpEntity<PersonForm> request = new HttpEntity<>(personForme, headers);
        restTemplate.put(url, request);

        return "redirect:/personList";
    }

}