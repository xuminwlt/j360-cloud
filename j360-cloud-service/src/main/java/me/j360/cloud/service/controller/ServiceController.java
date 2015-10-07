package me.j360.cloud.service.controller;


import me.j360.cloud.service.domain.Message;
import me.j360.cloud.service.domain.MessageAcknowledgement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    @Value("${reply.message}")
    private String message;

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public Resource<MessageAcknowledgement> pongMessage(@RequestBody Message input) {
        return new Resource<>(
                new MessageAcknowledgement(input.getId(), input.getPayload(), message));
    }



}
