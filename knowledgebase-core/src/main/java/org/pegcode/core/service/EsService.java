package org.pegcode.core.service;

import java.io.IOException;

public interface EsService {
    void createIndex(String indexName) throws IOException;

    void getIndex(String indexName) throws IOException;

    void existsIndex(String indexName) throws IOException;

    void userInsert() throws IOException;
}
