package com.d.erp.dbchange.listener.impl;

import com.d.erp.dbchange.DBChangeEvent;
import com.d.erp.dbchange.listener.DBChangeListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Component
public  class BaseApplicationListener<T extends DBChangeEvent> implements ApplicationListener, DBChangeListener {

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

        if( applicationEvent instanceof DBChangeEvent ){

            switch (((DBChangeEvent) applicationEvent).getType()){
                case "DELETE":
                    onDelete((DBChangeEvent) applicationEvent);
                    break;
                case "INSERT":
                case "UPDATE":
                    onUpdate((DBChangeEvent) applicationEvent);
                    break;
            }
        }
    }

    @Override
    public void onDelete(DBChangeEvent record) {
        invokeListenerMethod(record,"onDelete");
    }

    @Override
    public void onUpdate(DBChangeEvent record) {
        invokeListenerMethod(record,"onUpdate");

    }

    private void invokeListenerMethod(DBChangeEvent record , String methodName){
        String beenName = record.getEntityClass() + "Listener";
        if( applicationContext.containsBean(beenName)){
            DBChangeListener bean =  (DBChangeListener) applicationContext.getBean(beenName);
            Method method = ReflectionUtils.findMethod(bean.getClass(),methodName ,record.getClass());
            ReflectionUtils.invokeMethod( method, bean ,record);
        }
    }
}
