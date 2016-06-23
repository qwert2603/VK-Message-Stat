package com.qwert2603.vkmessagestat.base;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class BaseDialog<P extends BasePresenter> extends DialogFragment implements BaseView {

    private static final String presenterCodeKey = "presenterCodeKey";

    /**
     * Получить презентер, организующий работу этого диалога.
     *
     * @return презентер для этого диалога.
     */
    @NonNull
    protected abstract P getPresenter();

    protected abstract void setPresenter(@NonNull P presenter);

    @SuppressWarnings("unchecked")
    @CallSuper
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            int code = savedInstanceState.getInt(presenterCodeKey);
            BasePresenter presenter = loadPresenter(code);
            setPresenter((P) presenter);
        }
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(presenterCodeKey, savePresenter(getPresenter()));
        super.onSaveInstanceState(outState);
    }

    /**
     * Сохраненные презентеры.
     * @see #savePresenter(BasePresenter)
     * @see #loadPresenter(int)
     */
    private static final Map<Integer, BasePresenter> sPresenters = new HashMap<>();

    private static final Random sRandom = new Random();

    /**
     * Сохранить presenter.
     * @return код сохраненного presenter'a.
     */
    private static int savePresenter(BasePresenter presenter) {
        int code;
        do {
            code = sRandom.nextInt();
        } while (sPresenters.containsKey(code));
        sPresenters.put(code, presenter);
        return code;
    }

    /**
     * Загрузить сохраненный presenter.
     * После загрузки presenter удаляется ихз созраненных.
     * @param code код сохраненного presenter'a.
     */
    private static BasePresenter loadPresenter(int code) {
        BasePresenter presenter = sPresenters.get(code);
        sPresenters.remove(code);
        return presenter;
    }

}
