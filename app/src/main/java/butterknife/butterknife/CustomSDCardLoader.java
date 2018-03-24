package butterknife.butterknife;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

import skin.support.load.SkinSDCardLoader;

/**
 * Created by Jocerly on 2018/3/24.
 */
public class CustomSDCardLoader extends SkinSDCardLoader {
    public static final int SKIN_LOADER_STRATEGY_SDCARD = Integer.MAX_VALUE;
    public static final String SD_DIR = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String SKIN_DIR = SD_DIR + File.separator + "skins";

    @Override
    protected String getSkinPath(Context context, String skinName) {
        File file = new File(SKIN_DIR);
        if (!file.exists()) {
            file.mkdir();
            Log.d("skin", file.getAbsolutePath());
        }
        return new File(SKIN_DIR, skinName).getAbsolutePath();
    }

    @Override
    public int getType() {
        return SKIN_LOADER_STRATEGY_SDCARD;
    }
}
