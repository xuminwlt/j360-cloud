package me.j360.cloud.clientui.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import me.j360.cloud.clientui.domain.Message;
import me.j360.cloud.clientui.domain.MessageAcknowledgement;
import me.j360.cloud.clientui.feign.ServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("hystrixServiceClient")
public class HystrixWrappedServiceClient implements ServiceClient {

    @Autowired
    @Qualifier("serviceClient")
    private ServiceClient feignPongClient;

    @Override
    @HystrixCommand(groupKey = "serviceGroup", fallbackMethod = "fallBackCall")
    public MessageAcknowledgement sendMessage(Message message) {
        return this.feignPongClient.sendMessage(message);
    }

    public MessageAcknowledgement fallBackCall(Message message) {
        MessageAcknowledgement fallback = new MessageAcknowledgement(message.getId(), message.getPayload(), "FAILED SERVICE CALL! - FALLING BACK");
        return fallback;
    }
}
