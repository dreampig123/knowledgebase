package org.pegcode.core.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pegcode.core.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EsServiceImpl implements EsService {

    @Autowired
    private ElasticsearchClient client;

    //low level REST client
    @Override
    public void createIndex(String indexName) throws IOException {
        log.info("创建索引");
        //写法比RestHighLevelClient更加简洁
        CreateIndexResponse indexResponse = client.indices().create(c -> c.index(indexName));
        log.info(String.valueOf(indexResponse));
    }

    @Override
    public void getIndex(String indexName) throws IOException {
        log.info("查询索引");
        GetIndexResponse getIndexResponse = client.indices().get(i -> i.index(indexName));
        log.info(String.valueOf(getIndexResponse));
    }

    @Override
    public void existsIndex(String indexName) throws IOException {
        log.info("测试index是否存在");
        BooleanResponse booleanResponse = client.indices().exists(e -> e.index(indexName));
        log.info(String.valueOf(booleanResponse.value()));
    }

    @Override
    public void userInsert() throws IOException {
        log.info("user信息插入");
        //创建user列表
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId("18");
        user.setName("李三");
        user.setAge(13);
        user.setSex("男");
        users.add(user);

        List<BulkOperation> bulkOperations = new ArrayList<>();
        //将user中id作为es id，也可不指定id es会自动生成id
        users.forEach(a -> bulkOperations.add(BulkOperation.of(b -> b.index(c -> c.id(a.getId()).document(a)))));
        client.bulk(x ->x.index("user").operations(bulkOperations));

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class User{
        private String id;
        private String name;
        private String sex;
        private int age;
    }
}
