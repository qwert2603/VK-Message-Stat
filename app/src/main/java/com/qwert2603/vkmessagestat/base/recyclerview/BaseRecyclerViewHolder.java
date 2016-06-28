package com.qwert2603.vkmessagestat.base.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qwert2603.vkmessagestat.base.BasePresenter;
import com.qwert2603.vkmessagestat.base.BaseView;
import com.qwert2603.vkmessagestat.model.Identifiable;

/**
 * Класс ViewHolder, отвечающий за отображение данных в отдельном элементе
 * и хранящий ссылки на отображаемые View (TextView, например).
 */
public abstract class BaseRecyclerViewHolder<M extends Identifiable, P extends BasePresenter>
        extends RecyclerView.ViewHolder implements BaseView {

    public BaseRecyclerViewHolder(View itemView, BaseRecyclerViewAdapter adapter) {
        super(itemView);
        // назначаем callback'и для клика и долгого клика по элементу.
        itemView.setOnClickListener(v -> {
            if (adapter.getClickCallbacks() != null) {
                adapter.getClickCallbacks().onItemClicked(getLayoutPosition());
            }
        });
        itemView.setOnLongClickListener(v -> {
            if (adapter.getLongClickCallbacks() != null) {
                adapter.getLongClickCallbacks().onItemLongClicked(getLayoutPosition());
            }
            return true;
        });
    }

    /**
     * Получить презентер, организующий работу этого элемента.
     *
     * @return презентер, организующий работу элемента.
     */
    protected abstract P getPresenter();

    /**
     * Назначить модель для этого элемента.
     *
     * @param model объект модели.
     */
    public abstract void setModel(M model);

    /**
     * Привязать презентер, предназначенный для этого ViewHolder'a.
     */
    @SuppressWarnings("unchecked")
    public void bindPresenter() {
        getPresenter().bindView(BaseRecyclerViewHolder.this);
        getPresenter().onViewReady();
    }

    /**
     * Отвязать презентер, предназначенный для этого ViewHolder'a.
     */
    public void unbindPresenter() {
        getPresenter().onViewNotReady();
        getPresenter().unbindView();
    }
}
