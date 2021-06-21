package com.d.erp.dbchange;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class DBChangeRecord {

    private Integer id;

    private String database;

    private String table;

    private Boolean isDdl;

    private String type;

    private List data;

}
