package com.emzy.hello_chatgpt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final ChatClient client;

    @Autowired
    public BookService(ChatClient.Builder client) {
        this.client = client.build();
    }

    public Book getBook() {
        return client.prompt()
                .user("Generate a culinary book recommendation for lazy people")
                .call()
                .entity(Book.class);
    }
}
