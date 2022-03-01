package com.practice.rest.webservices.restwebservices.versioining;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    //via url
    @GetMapping("/v1/person")
    public PersonV1 personV1()
    {
        return new PersonV1("Babes");
    }

    //via headers
    @GetMapping(value="/person/header",headers = "X-API-VERSION=1")
    public PersonV1 headerV1()
    {
        return new PersonV1("BabesHeader");
    }

    @GetMapping(value="/person/header",headers = "X-API-VERSION=2")
    public PersonV2 headerV2()
    {
        return new PersonV2(new Name("BabesHeader","1"));
    }

    //produces
    @GetMapping(value="/person/produces",produces = "application/vnd.company.app-v1+json")
    public PersonV1 producesV1()
    {
        return new PersonV1("BabesProduces");
    }

    @GetMapping(value="/person/produces",headers = "application/vnd.company.app-v1+json")
    public PersonV2 producesV2()
    {
        return new PersonV2(new Name("BabesProduces","1"));
    }


}
