package butterknife.butterknife;

import android.app.Application;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.design.SkinMaterialManager;
import skin.support.design.app.SkinMaterialViewInflater;

/**
 * Created by Jocerly on 2018/3/24.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SkinMaterialManager.init(this);
        SkinCompatManager.init(this).loadSkin();
        initSkinSupport(this);
        SkinCompatManager.withoutActivity(this).addStrategy(new CustomSDCardLoader());
    }

    /**
     * 初始化换肤控件
     */
    private void initSkinSupport(Application application) {
        SkinCompatManager.withoutActivity(application)
                .addInflater(new SkinMaterialViewInflater())    // material design
                .addInflater(new SkinConstraintViewInflater())  // ConstraintLayout
                .addInflater(new SkinCardViewInflater())  // H07000223/FlycoTabLayout
                .setSkinStatusBarColorEnable(false)
                .loadSkin();
    }
}
