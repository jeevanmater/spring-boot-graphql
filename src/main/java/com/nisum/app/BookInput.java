package com.nisum.app;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record BookInput(String title, String author) implements Serializable {
}
