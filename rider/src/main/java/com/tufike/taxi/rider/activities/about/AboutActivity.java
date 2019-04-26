package com.tufike.taxi.rider.activities.about;

import android.os.Bundle;
import android.view.View;

import com.tufike.taxi.common.components.BaseActivity;
import com.tufike.taxi.rider.BuildConfig;
import com.tufike.taxi.rider.R;

import de.psdev.licensesdialog.LicensesDialog;
import de.psdev.licensesdialog.licenses.ApacheSoftwareLicense20;
import de.psdev.licensesdialog.licenses.BSD3ClauseLicense;
import de.psdev.licensesdialog.licenses.MITLicense;
import de.psdev.licensesdialog.model.Notice;
import de.psdev.licensesdialog.model.Notices;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        registerEventBus = false;
        super.onCreate(savedInstanceState);
        Element versionElement = new Element().setTitle(getString(R.string.version_name,BuildConfig.VERSION_NAME));
        Element licenseElement = new Element().setTitle(getString(R.string.legal_notices)).setIconDrawable(R.drawable.copyright).setOnClickListener(v -> {
            final Notices notices = new Notices();
            notices.addNotice(new Notice("Android Support Library", "https://developer.android.com/topic/libraries/support-library/index.html", "Google Inc.", new ApacheSoftwareLicense20()));
            notices.addNotice(new Notice("Android About Page", "https://github.com/medyo/android-about-page", "Mehdi Sakout", new MITLicense()));
            notices.addNotice(new Notice("EventBus", "http://greenrobot.org/eventbus/", "Markus Junginger, greenrobot", new ApacheSoftwareLicense20()));
            notices.addNotice(new Notice("Gson", "https://github.com/google/gson", "Google Inc.", new ApacheSoftwareLicense20()));
            notices.addNotice(new Notice("uCrop", "https://github.com/yalantis/ucrop", "Yalantis", new ApacheSoftwareLicense20()));
            notices.addNotice(new Notice("Lottie", "https://github.com/airbnb/lottie-android", "AirBnb", new ApacheSoftwareLicense20()));
            notices.addNotice(new Notice("TedBottomPicker", "https://github.com/ParkSangGwon/TedBottomPicker", "AirBnb", new ApacheSoftwareLicense20()));
            notices.addNotice(new Notice("CircleImageView", "https://github.com/hdodenhof/CircleImageView", "Henning Dodenhof", new ApacheSoftwareLicense20()));
            notices.addNotice(new Notice("Glide", "https://github.com/bumptech/glide", "Google Inc.", new BSD3ClauseLicense()));
            notices.addNotice(new Notice("StickyListHeaders", "https://github.com/emilsjolander/StickyListHeaders", "Emil Sjölander", new ApacheSoftwareLicense20()));
            notices.addNotice(new Notice("ColorPreference", "https://github.com/kizitonwose/colorpreference", "Kizito Nwose, Roman Nurik", new ApacheSoftwareLicense20()));
            notices.addNotice(new Notice("Google Play Services", "https://developer.android.com/index.html", "Google Inc.", new ApacheSoftwareLicense20()));
            notices.addNotice(new Notice("android-maps-utils", "https://github.com/googlemaps/android-maps-utils", "Google Inc.", new ApacheSoftwareLicense20()));
            notices.addNotice(new Notice("Alerter", "https://github.com/Tapadoo/Alerter", "Tapadoo, Dublin", new MITLicense()));
            new LicensesDialog.Builder(AboutActivity.this)
                    .setNotices(notices)
                    .setIncludeOwnLicense(true)
                    .build()
                    .show();
        });
        AboutPage aboutPage = new AboutPage(AboutActivity.this)
                .isRTL(false)
                .setImage(R.drawable.logo)
                .addItem(versionElement)
                .addGroup(getString(R.string.about_contacts));
        if(!getString(R.string.email).equals(""))
            aboutPage.addEmail(getString(R.string.email));
        if(!getString(R.string.website).equals(""))
            aboutPage.addWebsite(getString(R.string.website));
        if(!getString(R.string.twitter).equals(""))
            aboutPage.addTwitter(getString(R.string.twitter));
        if(!getString(R.string.instagram).equals(""))
            aboutPage.addInstagram(getString(R.string.instagram));
        if(!getString(R.string.facebook).equals(""))
            aboutPage.addFacebook(getString(R.string.facebook));
        if(!getString(R.string.playStore).equals(""))
            aboutPage.addPlayStore(getString(R.string.playStore));
        aboutPage.addItem(licenseElement);
        aboutPage.setDescription(getString(R.string.about_rider_description));
        View view = aboutPage.create();
        setContentView(view);
    }
}
