package com.emzy.hello_chatgpt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final ChatClient client;

    public BookController(ChatClient.Builder client) {
        this.client = client.build();
    }

    @GetMapping("/")
    public Book getBookInfo() {
        System.out.println("hello");
        return client.prompt()
                .user("Generate a culinary book recommendation for lazy people")
                .call()
                .entity(Book.class);
    }
}
