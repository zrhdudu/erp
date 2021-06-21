package com.d.erp.dbchange.listener;

import com.d.erp.dbchange.DBChangeEvent;

public interface DBChangeListener {

     void onDelete(DBChangeEvent record);

     void onUpdate(DBChangeEvent record);

}
