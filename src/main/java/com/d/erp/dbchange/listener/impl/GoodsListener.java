package com.d.erp.dbchange.listener.impl;

import com.d.erp.dbchange.DBChangeEvent;
import com.d.erp.dbchange.listener.DBChangeListener;
import org.springframework.stereotype.Component;

@Component
public class GoodsListener implements DBChangeListener {


    @Override
    public void onDelete(DBChangeEvent record) {
        System.out.println(record);
    }

    @Override
    public void onUpdate(DBChangeEvent record) {
        System.out.println(record);
    }

}
