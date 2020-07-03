package com.fancyliu.learning.elasticsearch;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class AssetTest {

    // 连接客户端
    // 暂时先不用yml配置中的地址，这里后续要优化
    TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
            .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));

    public AssetTest() throws UnknownHostException {
    }

    @Test
    public void indexDocument() {
        // 生成JSON数据
        Map<String, Object> json = new HashMap<>();
        json.put("name", "JoeZ");
        json.put("postDate", new Date());
        json.put("age", 18);
        // 把文档编入索引
        IndexResponse indexResponse = client.prepareIndex("twitter", "tweet", "3")
                .setSource(json, XContentType.JSON)
                .get();
        client.close();
    }

    /**
     * 获取API
     */
    @Test
    public void getIndex() {
        GetResponse getFields = client.prepareGet("twitter", "tweet", "1").get();
        log.info("获取数据为" + getFields);
    }

    /**
     * 删除API
     */
    @Test
    public void delIndex() {
        DeleteResponse deleteResponse = client.prepareDelete("twitter", "tweet", "1").get();
        log.info("删除API");
    }

    /**
     * 更新API
     */
    @Test
    public void upteApi() throws IOException, ExecutionException, InterruptedException {
        UpdateRequest updateResponse = new UpdateRequest("twitter", "tweet", "1")
                .doc(XContentFactory.jsonBuilder().startObject().field("name", "李四")
                        .endObject());
        client.update(updateResponse).get();
        log.info("更新API");
    }
}
