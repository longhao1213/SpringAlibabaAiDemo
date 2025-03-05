package com.Alibaba.ai.demo;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

@SpringBootApplication
public class AlibabaAiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaAiDemoApplication.class, args);
    }

    @Bean
    public ChatMemory chatMemory() {
        return new InMemoryChatMemory();
    }

    @Bean
    CommandLineRunner ingestTermOfServiceToVectorStore(VectorStore vectorStore,
                                                       @Value("classpath:rag/terms-of-service.txt")Resource termsOfSrviceDoc) {
        return args -> {
            vectorStore.write(
                    new TokenTextSplitter().transform((
                            new TextReader(termsOfSrviceDoc).read()
                            ))
            );
        };
    }

    @Bean
    public VectorStore vectorStore(EmbeddingModel embeddingModel) {
        SimpleVectorStore.SimpleVectorStoreBuilder SimpleVectorStoreBuilder = SimpleVectorStore.builder(embeddingModel);
        return SimpleVectorStoreBuilder.build();
    }
}
