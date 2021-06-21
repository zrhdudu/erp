package com.d.erp.configuration;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.d.erp.dbchange.DBChangeRecord;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DBChangeDeserializer extends JsonDeserializer<DBChangeRecord> implements InitializingBean {

    @Value("${mybatis-plus.global-config.db-config.table-prefix:''}")
    private String tablePrefix;

    @Value("${mybatis-plus.type-aliases-package:''}")
    private String entityPackage;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void afterPropertiesSet() throws Exception {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    }

    @SneakyThrows
    @Override
    public DBChangeRecord deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String table = node.get("table").asText();
        String type = node.get("type").asText();
        String entityClass = entityPackage +"."+ StringUtils.underlineToCamel(table).replace(StringUtils.underlineToCamel(tablePrefix),"");
        Class entity = Class.forName(entityClass);
        JsonNode listNode = node.path("data");
        List data = new ArrayList<>();
        listNode.forEach( dataNode ->{
            try {
                data.add(mapper.readValue(mapper.writeValueAsString(dataNode),entity));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

        final val record = new DBChangeRecord();
        record.setTable(table);
        record.setType(type);
        record.setData(data);
        return record;
    }

    public static void main(String[] args) {
        String table = "erp_goods";

        System.out.println(StringUtils.underlineToCamel(table).replace(StringUtils.underlineToCamel("erp_"),""));
    }
}
