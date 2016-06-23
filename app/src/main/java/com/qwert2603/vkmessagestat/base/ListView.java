package com.qwert2603.vkmessagestat.base;

import java.util.List;

/**
 * Представление списка для шаблона MVP.
 *
 * @param <T> тип элемента списка.
 */
public interface ListView<T> extends BaseView {

    /**
     * Отобразить сообщение о загрузке.
     */
    void showLoading();

    /**
     * Отобразить сообщение об ошибке загрузки.
     */
    void showError();

    /**
     * Отобразить сообщение о том, что список пуст.
     */
    void showEmpty();

    /**
     * Отобразить список.
     *
     * @param list список для отображения.
     */
    void showList(List<T> list);

}
