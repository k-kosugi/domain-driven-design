package com.microsoft.japan.ddd.catalog.adapter.out.ai.openapi;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Component;

import com.microsoft.japan.ddd.catalog.application.port.out.EmbeddingPort;

@Component
public class EmbeddingPortImpl implements EmbeddingPort {

    private final EmbeddingModel embeddingModel;

    public EmbeddingPortImpl(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    @Override
    public float[] getEmbedding(String queryText) {
        return this.embeddingModel.embed(queryText);
    }

}
