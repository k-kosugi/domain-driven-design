package com.microsoft.japan.ddd.catalog.application.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record BookDTO(String isbn13, String title, String contents, List<AuthorDTO> authors, PublisherDTO publisher, @JsonIgnore float[] embedding) {
}
