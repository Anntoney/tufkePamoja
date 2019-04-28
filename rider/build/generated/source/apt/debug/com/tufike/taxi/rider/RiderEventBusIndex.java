package com.tufike.taxi.rider;

import org.greenrobot.eventbus.meta.SimpleSubscriberInfo;
import org.greenrobot.eventbus.meta.SubscriberMethodInfo;
import org.greenrobot.eventbus.meta.SubscriberInfo;
import org.greenrobot.eventbus.meta.SubscriberInfoIndex;

import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

/** This class is generated by EventBus, do not edit. */
public class RiderEventBusIndex implements SubscriberInfoIndex {
    private static final Map<Class<?>, SubscriberInfo> SUBSCRIBER_INDEX;

    static {
        SUBSCRIBER_INDEX = new HashMap<Class<?>, SubscriberInfo>();

        putIndex(new SimpleSubscriberInfo(com.tufike.taxi.rider.activities.looking.LookingActivity.class, true,
                new SubscriberMethodInfo[] {
            new SubscriberMethodInfo("onDriverAccepted", com.tufike.taxi.rider.events.DriverAcceptedEvent.class,
                    ThreadMode.MAIN),
        }));

        putIndex(new SimpleSubscriberInfo(com.tufike.taxi.rider.activities.addresses.AddressesActivity.class, true,
                new SubscriberMethodInfo[] {
            new SubscriberMethodInfo("onCRUDResultReceived", com.tufike.taxi.rider.events.CRUDAddressResultEvent.class,
                    ThreadMode.MAIN),
        }));

        putIndex(new SimpleSubscriberInfo(com.tufike.taxi.rider.ui.RiderBaseActivity.class, true,
                new SubscriberMethodInfo[] {
            new SubscriberMethodInfo("onServiceStarted",
                    com.tufike.taxi.common.events.BackgroundServiceStartedEvent.class),
            new SubscriberMethodInfo("onConnectedResult", com.tufike.taxi.common.events.ConnectResultEvent.class,
                    ThreadMode.MAIN),
        }));

        putIndex(new SimpleSubscriberInfo(com.tufike.taxi.rider.activities.splash.SplashActivity.class, true,
                new SubscriberMethodInfo[] {
            new SubscriberMethodInfo("onLoginResultEvent", com.tufike.taxi.rider.events.LoginResultEvent.class,
                    ThreadMode.MAIN),
            new SubscriberMethodInfo("onConnectedResult", com.tufike.taxi.common.events.ConnectResultEvent.class,
                    ThreadMode.MAIN),
            new SubscriberMethodInfo("onServiceStarted",
                    com.tufike.taxi.common.events.BackgroundServiceStartedEvent.class),
        }));

        putIndex(new SimpleSubscriberInfo(com.tufike.taxi.rider.activities.coupon.CouponActivity.class, true,
                new SubscriberMethodInfo[] {
            new SubscriberMethodInfo("onCouponsResultEvent", com.tufike.taxi.rider.events.GetCouponsResultEvent.class,
                    ThreadMode.MAIN),
            new SubscriberMethodInfo("onApplyCouponResultEvent",
                    com.tufike.taxi.rider.events.ApplyCouponResultEvent.class, ThreadMode.MAIN),
            new SubscriberMethodInfo("onAddCouponResultEvent", com.tufike.taxi.rider.events.AddCouponResultEvent.class,
                    ThreadMode.MAIN),
        }));

        putIndex(new SimpleSubscriberInfo(com.tufike.taxi.rider.activities.travel.TravelActivity.class, true,
                new SubscriberMethodInfo[] {
            new SubscriberMethodInfo("onServiceFinished", com.tufike.taxi.rider.events.ServiceFinishedEvent.class,
                    ThreadMode.MAIN),
            new SubscriberMethodInfo("onServiceCanceled", com.tufike.taxi.common.events.ServiceCancelResultEvent.class,
                    ThreadMode.MAIN),
            new SubscriberMethodInfo("onReviewResult", com.tufike.taxi.rider.events.ReviewDriverResultEvent.class,
                    ThreadMode.MAIN),
            new SubscriberMethodInfo("onTravelStarted", com.tufike.taxi.rider.events.ServiceStartedEvent.class,
                    ThreadMode.MAIN),
            new SubscriberMethodInfo("onCallRequested",
                    com.tufike.taxi.common.events.ServiceCallRequestResultEvent.class, ThreadMode.MAIN),
        }));

        putIndex(new SimpleSubscriberInfo(com.tufike.taxi.rider.services.RiderService.class, true,
                new SubscriberMethodInfo[] {
            new SubscriberMethodInfo("connectSocket", com.tufike.taxi.common.events.ConnectEvent.class),
            new SubscriberMethodInfo("login", com.tufike.taxi.common.events.LoginEvent.class),
            new SubscriberMethodInfo("getStatus", com.tufike.taxi.common.events.GetStatusEvent.class),
            new SubscriberMethodInfo("EditProfile", com.tufike.taxi.common.events.EditProfileInfoEvent.class),
            new SubscriberMethodInfo("requestTaxi", com.tufike.taxi.rider.events.ServiceRequestEvent.class),
            new SubscriberMethodInfo("cancelTravel", com.tufike.taxi.common.events.ServiceCancelEvent.class),
            new SubscriberMethodInfo("cancelRequest", com.tufike.taxi.rider.events.CancelRequestRequestEvent.class),
            new SubscriberMethodInfo("acceptDriver", com.tufike.taxi.rider.events.AcceptDriverEvent.class),
            new SubscriberMethodInfo("getTravels", com.tufike.taxi.common.events.GetTravelsEvent.class),
            new SubscriberMethodInfo("getDriverLocation", com.tufike.taxi.rider.events.GetTravelInfoEvent.class),
            new SubscriberMethodInfo("reviewDriver", com.tufike.taxi.rider.events.ReviewDriverEvent.class),
            new SubscriberMethodInfo("ChangeProfileImage",
                    com.tufike.taxi.common.events.ChangeProfileImageEvent.class),
            new SubscriberMethodInfo("getDriversLocation", com.tufike.taxi.rider.events.GetDriversLocationEvent.class),
            new SubscriberMethodInfo("WriteComplaint", com.tufike.taxi.common.events.WriteComplaintEvent.class),
            new SubscriberMethodInfo("chargeAccount", com.tufike.taxi.common.events.ChargeAccountEvent.class),
            new SubscriberMethodInfo("HideTravel", com.tufike.taxi.common.events.HideTravelEvent.class),
            new SubscriberMethodInfo("callRequest", com.tufike.taxi.common.events.ServiceCallRequestEvent.class),
            new SubscriberMethodInfo("onCalculateFareRequested",
                    com.tufike.taxi.rider.events.CalculateFareRequestEvent.class),
            new SubscriberMethodInfo("crudAddress", com.tufike.taxi.rider.events.CRUDAddressRequestEvent.class),
            new SubscriberMethodInfo("getCoupons", com.tufike.taxi.rider.events.GetCouponsRequestEvent.class),
            new SubscriberMethodInfo("getPromotions", com.tufike.taxi.rider.events.GetPromotionsRequestEvent.class),
            new SubscriberMethodInfo("applyCoupon", com.tufike.taxi.rider.events.ApplyCouponRequestEvent.class),
            new SubscriberMethodInfo("addCoupon", com.tufike.taxi.rider.events.AddCouponRequestEvent.class),
            new SubscriberMethodInfo("getTransactions",
                    com.tufike.taxi.common.events.GetTransactionsRequestEvent.class),
            new SubscriberMethodInfo("notificationPlayerId", com.tufike.taxi.common.events.NotificationPlayerId.class),
        }));

        putIndex(new SimpleSubscriberInfo(com.tufike.taxi.rider.activities.profile.ProfileActivity.class, true,
                new SubscriberMethodInfo[] {
            new SubscriberMethodInfo("onProfileInfoChanged",
                    com.tufike.taxi.common.events.EditProfileInfoResultEvent.class, ThreadMode.MAIN),
            new SubscriberMethodInfo("onProfileImageChanged",
                    com.tufike.taxi.common.events.ChangeProfileImageResultEvent.class, ThreadMode.MAIN),
        }));

        putIndex(new SimpleSubscriberInfo(com.tufike.taxi.rider.activities.main.MainActivity.class, true,
                new SubscriberMethodInfo[] {
            new SubscriberMethodInfo("onAddressesReceived", com.tufike.taxi.rider.events.CRUDAddressResultEvent.class,
                    ThreadMode.MAIN),
            new SubscriberMethodInfo("onServiceRequestResult",
                    com.tufike.taxi.rider.events.ServiceRequestResultEvent.class, ThreadMode.MAIN),
            new SubscriberMethodInfo("onCalculateFareReceived",
                    com.tufike.taxi.rider.events.CalculateFareResultEvent.class, ThreadMode.MAIN),
            new SubscriberMethodInfo("onDriversLocationResult",
                    com.tufike.taxi.rider.events.GetDriversLocationResultEvent.class, ThreadMode.MAIN),
            new SubscriberMethodInfo("onRequestTaxiError", com.tufike.taxi.rider.events.ServiceRequestErrorEvent.class,
                    ThreadMode.MAIN),
            new SubscriberMethodInfo("onProfileChanged", com.tufike.taxi.common.events.ProfileInfoChangedEvent.class,
                    ThreadMode.MAIN, 0, true),
            new SubscriberMethodInfo("OnGetStatusResultReceived",
                    com.tufike.taxi.common.events.GetStatusResultEvent.class, ThreadMode.MAIN),
        }));

        putIndex(new SimpleSubscriberInfo(com.tufike.taxi.rider.activities.promotions.PromotionsActivity.class, true,
                new SubscriberMethodInfo[] {
            new SubscriberMethodInfo("OnPromotionsReceived",
                    com.tufike.taxi.rider.events.GetPromotionsResultEvent.class, ThreadMode.MAIN),
        }));

        putIndex(new SimpleSubscriberInfo(com.tufike.taxi.rider.activities.travel.fragments.TabStatisticsFragment.class,
                true, new SubscriberMethodInfo[] {
            new SubscriberMethodInfo("onTravelInfoReceived",
                    com.tufike.taxi.rider.events.GetTravelInfoResultEvent.class, ThreadMode.MAIN),
        }));

        putIndex(new SimpleSubscriberInfo(com.tufike.taxi.rider.activities.main.dialogs.DriverAcceptedDialog.class,
                true, new SubscriberMethodInfo[] {
            new SubscriberMethodInfo("onServiceRequestResult",
                    com.tufike.taxi.rider.events.ServiceRequestResultEvent.class, ThreadMode.MAIN),
            new SubscriberMethodInfo("onDriverAccepted", com.tufike.taxi.rider.events.DriverAcceptedEvent.class,
                    ThreadMode.MAIN),
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
