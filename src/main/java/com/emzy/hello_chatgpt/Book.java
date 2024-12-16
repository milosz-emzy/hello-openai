package com.emzy.hello_chatgpt;

public record Book(
        String author,
        String title,
        String summary,
        String coverDescription,
        int pageCount,
        int publishedYear
) {
}
