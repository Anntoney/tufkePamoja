package com.tufike.taxi.common;

import org.greenrobot.eventbus.meta.SimpleSubscriberInfo;
import org.greenrobot.eventbus.meta.SubscriberMethodInfo;
import org.greenrobot.eventbus.meta.SubscriberInfo;
import org.greenrobot.eventbus.meta.SubscriberInfoIndex;

import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

/** This class is generated by EventBus, do not edit. */
public class ModelEventBusIndex implements SubscriberInfoIndex {
    private static final Map<Class<?>, SubscriberInfo> SUBSCRIBER_INDEX;

    static {
        SUBSCRIBER_INDEX = new HashMap<Class<?>, SubscriberInfo>();

        putIndex(new SimpleSubscriberInfo(com.tufike.taxi.common.activities.transactions.TransactionsActivity.class,
                true, new SubscriberMethodInfo[] {
            new SubscriberMethodInfo("OnTransactionsResultReceived",
                    com.tufike.taxi.common.events.GetTransactionsResultEvent.class, ThreadMode.MAIN),
        }));

        putIndex(new SimpleSubscriberInfo(com.tufike.taxi.common.activities.chargeAccount.ChargeAccountActivity.class,
                true, new SubscriberMethodInfo[] {
            new SubscriberMethodInfo("AccountCharged", com.tufike.taxi.common.events.ChargeAccountResultEvent.class,
                    ThreadMode.MAIN),
        }));

        putIndex(new SimpleSubscriberInfo(com.tufike.taxi.common.activities.login.LoginActivity.class, true,
                new SubscriberMethodInfo[] {
            new SubscriberMethodInfo("onRequestValidation",
                    com.tufike.taxi.common.activities.login.LoginActivity.RequestSMSEvent.class,
                            ThreadMode.BACKGROUND),
            new SubscriberMethodInfo("onRequestSMSResult",
                    com.tufike.taxi.common.activities.login.LoginActivity.RequestSMSResultEvent.class,
                            ThreadMode.MAIN),
            new SubscriberMethodInfo("onVerifyCode",
                    com.tufike.taxi.common.activities.login.LoginActivity.VerifyCodeEvent.class,
                            ThreadMode.BACKGROUND),
            new SubscriberMethodInfo("onVerifyResult",
                    com.tufike.taxi.common.activities.login.LoginActivity.VerifyCodeResultEvent.class,
                            ThreadMode.MAIN),
        }));

        putIndex(new SimpleSubscriberInfo(com.tufike.taxi.common.activities.travels.TravelsActivity.class, true,
                new SubscriberMethodInfo[] {
            new SubscriberMethodInfo("onWriteComplaintResult",
                    com.tufike.taxi.common.events.WriteComplaintResultEvent.class, ThreadMode.MAIN),
            new SubscriberMethodInfo("onHideTravelResult", com.tufike.taxi.common.events.HideTravelResultEvent.class,
                    ThreadMode.MAIN),
            new SubscriberMethodInfo("onTravelsReceived", com.tufike.taxi.common.events.GetTravelsResultEvent.class,
                    ThreadMode.MAIN),
        }));

        putIndex(new SimpleSubscriberInfo(com.tufike.taxi.common.components.BaseActivity.class, true,
                new SubscriberMethodInfo[] {
            new SubscriberMethodInfo("onConnectionEventReceived",
                    com.tufike.taxi.common.events.SocketConnectionEvent.class, ThreadMode.MAIN),
        }));

    }

    private static void putIndex(SubscriberInfo info) {
        SUBSCRIBER_INDEX.put(info.getSubscriberClass(), info);
    }

    @Override
    public SubscriberInfo getSubscriberInfo(Class<?> subscriberClass) {
        SubscriberInfo info = SUBSCRIBER_INDEX.get(subscriberClass);
        if (info != null) {
            return info;
        } else {
            return null;
        }
    }
}
