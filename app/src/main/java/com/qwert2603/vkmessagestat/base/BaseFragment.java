package com.qwert2603.vkmessagestat.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

/**
 * Базовый фрагмент, построенный для работы с шаблоном MVP.
 * Организует взаимодействие с презентером:
 * - привязка/отвязка {@link BasePresenter#bindView(BaseView)}, {@link BasePresenter#unbindView()}.
 * - уведомление о готовности {@link BasePresenter#onViewReady()}, {@link BasePresenter#onViewNotReady()}.
 *
 * @param <P> Тип презентера, организующего работу фрагмента.
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    /**
     * Получить презентер, организующий работу этого фрагмента.
     *
     * @return презентер для этого фрагмента.
     */
    @NonNull
    protected abstract P getPresenter();

    @SuppressWarnings("unchecked")
    @CallSuper
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Сохраняем состояние, чтобы не создавать презентер заново.
        // Это позволяет не прерывать загрузку, если презентер что-то загружает.
        setRetainInstance(true);
        getPresenter().bindView(this);
    }

    @CallSuper
    @Override
    public void onDestroy() {
        getPresenter().unbindView();
        super.onDestroy();
    }

    @CallSuper
    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onViewReady();
    }

    @CallSuper
    @Override
    public void onPause() {
        getPresenter().onViewNotReady();
        super.onPause();
    }

}