package com.qwert2603.vkmessagestat.base.recyclerview;

import android.support.v7.widget.RecyclerView;

import com.qwert2603.vkmessagestat.model.Identifiable;

import java.util.ArrayList;
import java.util.List;

/**
 * Базовый адаптер для {@link RecyclerView} для шаблона MVP.
 * Может передавать callback'и о нажатии и долгом нажатии на отдельный элемент.
 *
 * @param <M>  тип модели, отображаемой в каждом элементе.
 * @param <VH> тип объекта (ViewHolder), отвечающего за отображение данных в отдельном элементе.
 */
public abstract class BaseRecyclerViewAdapter<M extends Identifiable, VH extends BaseRecyclerViewHolder>
        extends RecyclerView.Adapter<VH> {

    private volatile List<M> mModelList = new ArrayList<>();
    private ClickCallbacks mClickCallbacks;

    public BaseRecyclerViewAdapter() {
    }

    /**
     * Назначить callback для нажатия на элемент.
     *
     * @param clickCallbacks callback для нажатия на элемент.
     */
    public void setClickCallbacks(ClickCallbacks clickCallbacks) {
        mClickCallbacks = clickCallbacks;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(VH holder, int position) {
        M model = mModelList.get(position);
        // назначаем модель viewHolder'у элемента.
        holder.bindPresenter();
        holder.setModel(model);
    }

    @Override
    public int getItemCount() {
        return mModelList.size();
    }

    @Override
    public void onViewRecycled(VH holder) {
        super.onViewRecycled(holder);
        // отвязываем презентер от переработанного представления.
        holder.unbindPresenter();
    }

    @Override
    public boolean onFailedToRecycleView(VH holder) {
        // в случае ошибки переработки отвязяваем презентер.
        holder.unbindPresenter();
        return super.onFailedToRecycleView(holder);
    }

    /**
     * Назначить список объектов модели для отображения.
     * @param modelList список объектов модели для отображения.
     */
    public void setModelList(List<M> modelList) {
        if (modelList != mModelList) {
            mModelList = modelList;
        }
        notifyDataSetChanged();
    }

    public ClickCallbacks getClickCallbacks() {
        return mClickCallbacks;
    }
}