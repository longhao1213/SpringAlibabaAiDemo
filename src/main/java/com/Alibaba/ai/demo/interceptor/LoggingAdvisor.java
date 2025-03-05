package com.Alibaba.ai.demo.interceptor;

import org.checkerframework.checker.units.qual.C;
import org.springframework.ai.chat.client.RequestResponseAdvisor;
import org.springframework.ai.chat.client.advisor.api.*;
import reactor.core.publisher.Flux;

public class LoggingAdvisor implements CallAroundAdvisor {

    @Override
    public String getName() {
        return "longsan";
    }

    @Override
    public int getOrder() {
        return 0;
    }


    @Override
    public AdvisedResponse aroundCall(AdvisedRequest advisedRequest, CallAroundAdvisorChain chain) {
        System.out.println(advisedRequest.userText());
        System.out.println(advisedRequest.chatOptions().toString());
        return chain.nextAroundCall(advisedRequest);
    }
}
