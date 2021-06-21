package com.d.erp.dbchange;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@ToString
public class DBChangeEvent extends ApplicationEvent {

    private Object value;

    private String Type;

    private String entityClass;

    public DBChangeEvent(Object source) {
        super(source);
        value = source;
        entityClass = StringUtils.firstToLowerCase(value.getClass().getSimpleName()) ;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }
}
