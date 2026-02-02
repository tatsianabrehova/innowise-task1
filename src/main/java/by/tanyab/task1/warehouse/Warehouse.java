package by.tanyab.task1.warehouse;

import by.tanyab.task1.entity.CustomArrayData;

public interface Warehouse {
    CustomArrayData getData(long id);
    boolean containsData(long id);
    void removeData(long id);
    void clearData();
    static Warehouse getInstance() {
        return null;
    }
}