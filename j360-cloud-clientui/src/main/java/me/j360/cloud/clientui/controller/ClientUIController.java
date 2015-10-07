package me.j360.cloud.clientui.controller;

import me.j360.cloud.clientui.domain.Message;
import me.j360.cloud.clientui.domain.MessageAcknowledgement;
import me.j360.cloud.clientui.feign.ServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientUIController {

    @Autowired
    @Qualifier("hystrixServiceClient")
    private ServiceClient serviceClient;

    @RequestMapping("/dispatch")
    public MessageAcknowledgement sendMessage(@RequestBody Message message) {
        return this.serviceClient.sendMessage(message);
    }
}
