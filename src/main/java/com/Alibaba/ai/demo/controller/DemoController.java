package com.Alibaba.ai.demo.controller;

import com.Alibaba.ai.demo.interceptor.LoggingAdvisor;
import org.checkerframework.checker.units.qual.C;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

@RestController
@CrossOrigin
public class DemoController {
    private final ChatClient chatClient;

    public DemoController(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory, VectorStore vectorStore) {
        this.chatClient = chatClientBuilder
                .defaultSystem("""
                        你是龙三的个人助理，你叫小雨儿，你需要友好乐于助人。
                        你正在和龙三聊天
                        在询问用户之前，请检查消息历史记录以获取用户姓名等信息，尽量避免重复询问给用户造成困扰。
                        当我需要获取我的订单的时候，你需要询问我的用户名,如果用户已经说了用户名那么直接去查询
                        当我要删除订单的时候，你需要询问我被删掉的订单名称
                        请说中文
                        今天的日期是 {current_date}.
                        """)
                .defaultAdvisors(
                        // 附加对话记录
                        new PromptChatMemoryAdvisor(chatMemory)
//                        ,
                        // 日志拦截
//                        new LoggingAdvisor()
                        ,
                        new QuestionAnswerAdvisor(vectorStore, SearchRequest.builder().build())
                ).defaultFunctions("getOrder","deleteOrder")
                .build();
    }

    @GetMapping("/chat")
    public String chat(String input) {
        return this.chatClient.prompt()
                .user(input)
                .system(s->s.param("current_date", LocalDateTime.now().toString()))
                // 对话存储限制
                .advisors(a->a.param(CHAT_MEMORY_RETRIEVE_SIZE_KEY,100))
                .call()
                .content();
    }
}
