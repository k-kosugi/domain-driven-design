package com.microsoft.japan.ddd.catalog.application.port.out;

public interface EmbeddingPort {
    float[] getEmbedding(String text);
}
