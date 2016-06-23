package com.qwert2603.vkmessagestat.base;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
 * @param <P>  тип презентера, организующего работу отдельного элемента.
 */
public abstract class BaseRecyclerViewAdapter
        <M extends Identifiable, VH extends BaseRecyclerViewAdapter.RecyclerViewHolder, P extends BasePresenter>
        extends RecyclerView.Adapter<VH> {

    /**
     * Callback для нажатия на элемент.
     */
    public interface ClickCallbacks {
        /**
         * Нажатие на элемент.
         *
         * @param position позиция нажатого элемента
         */
        void onItemClicked(int position);
    }

    /**
     * Callback для долгого нажатия на элемент.
     */
    public interface LongClickCallbacks {
        /**
         * Долгое нажатие на элемент.
         *
         * @param position позиция элемента, на который было долгое нажатие
         */
        void onItemLongClicked(int position);
    }

    private volatile List<M> mModelList = new ArrayList<>();
    private ClickCallbacks mClickCallbacks;
    private LongClickCallbacks mLongClickCallbacks;
    private RecyclerViewSelector mRecyclerViewSelector = new RecyclerViewSelector();

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

    /**
     * Класс для выделения отдельного элемента.
     */
    public class RecyclerViewSelector {
        private int mSelectedPosition = -1;

        /**
         * Установить позицию выделенного элемент.
         *
         * @param selectedPosition позиция выделенного элемента.
         */
        public void setSelectedPosition(int selectedPosition) {
            int oldSelectedPosition = mSelectedPosition;
            mSelectedPosition = selectedPosition;
            notifyItemChanged(oldSelectedPosition);
            notifyItemChanged(mSelectedPosition);
        }

        /**
         * Отобразить выделен элемент или нет.
         *
         * @param itemView view элемента.
         * @param position позиция элемента.
         */
        public void showWhetherItemSelected(View itemView, int position) {
            itemView.setSelected(position == mSelectedPosition);
        }
    }

    /**
     * Класс ViewHolder, отвечающий за отображение данных в отдельном элементе
     * и хранящий ссылки на отображаемые View (TextView, например).
     */
    public abstract class RecyclerViewHolder extends RecyclerView.ViewHolder implements BaseView {

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            // назначаем callback'и для клика и долгого клика по элементу.
            itemView.setOnClickListener(v -> {
                if (mClickCallbacks != null) {
                    mClickCallbacks.onItemClicked(getLayoutPosition());
                }
            });
            itemView.setOnLongClickListener(v -> {
                if (mLongClickCallbacks != null) {
                    mLongClickCallbacks.onItemLongClicked(getLayoutPosition());
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
         * @param oneResult объект модели.
         */
        protected abstract void setModel(M oneResult);

        /**
         * Привязать презентер, предназначенный для этого ViewHolder'a.
         */
        @SuppressWarnings("unchecked")
        public void bindPresenter() {
            getPresenter().bindView(RecyclerViewHolder.this);
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

}