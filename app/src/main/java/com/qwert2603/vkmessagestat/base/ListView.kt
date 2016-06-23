package com.qwert2603.vkmessagestat.base

/**
 * Представление списка для шаблона MVP.
 *
 * @param T тип элемента списка.
 */
interface ListView<T> : BaseView {

    /**
     * Отобразить сообщение о загрузке.
     */
    fun showLoading()

    /**
     * Отобразить сообщение об ошибке загрузки.
     */
    fun showError()

    /**
     * Отобразить сообщение о том, что список пуст.
     */
    fun showEmpty()

    /**
     * Отобразить список.

     * @param list список для отображения.
     */
    fun showList(list: List<T>)

}
