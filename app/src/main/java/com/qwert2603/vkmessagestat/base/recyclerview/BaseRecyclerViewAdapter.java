package com.qwert2603.vkmessagestat.base.recyclerview;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.qwert2603.vkmessagestat.model.Identifiable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Базовый адаптер для {@link RecyclerView} для шаблона MVP.
 * Может передавать callback'и о нажатии и долгом нажатии на отдельный элемент.
 * {@link #setClickCallbacks(ClickCallbacks)}, {@link #setLongClickCallbacks(LongClickCallbacks)}.
 * Позволяет выделять отдельный элемент {@link #setSelectedItemPosition(int)}.
 *
 * @param <M>  тип модели, отображаемой в каждом элементе.
 * @param <VH> тип объекта (ViewHolder), отвечающего за отображение данных в отдельном элементе.
 */
public abstract class BaseRecyclerViewAdapter
        <M extends Identifiable, VH extends BaseRecyclerViewHolder>
        extends RecyclerView.Adapter<VH> {

    private volatile List<M> mModelList = new ArrayList<>();
    private ClickCallbacks mClickCallbacks;
    private LongClickCallbacks mLongClickCallbacks;
    private RecyclerViewSelector mRecyclerViewSelector = new RecyclerViewSelector(BaseRecyclerViewAdapter.this);

    /**
     * Карта id модели -- ее ViewHolder.
     * Если нет, то null.
     */
    private Map<Integer, VH> mVHMap = new HashMap<>();

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

    /**
     * Назначить callback для долгого нажатия на элемент.
     *
     * @param longClickCallbacks callback для долгого нажатия на элемент.
     */
    public void setLongClickCallbacks(LongClickCallbacks longClickCallbacks) {
        mLongClickCallbacks = longClickCallbacks;
    }

    /**
     * Установить позицию выделенного элемент.
     *
     * @param position позиция выделенного элемента.
     */
    public void setSelectedItemPosition(int position) {
        mRecyclerViewSelector.setSelectedPosition(position);
    }

    @Nullable
    public VH getViewHolderForModel(int modelId) {
        return mVHMap.get(modelId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(VH holder, int position) {
        M model = mModelList.get(position);
        // назначаем модель viewHolder'у элемента.
        holder.setModel(model);
        holder.bindPresenter();
        // отображаем выделен элемент или нет.
        mRecyclerViewSelector.showWhetherItemSelected(holder.itemView, position);
        mVHMap.put(model.getId(), holder);
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

    public LongClickCallbacks getLongClickCallbacks() {
        return mLongClickCallbacks;
    }
}