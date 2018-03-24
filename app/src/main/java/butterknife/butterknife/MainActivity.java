package butterknife.butterknife;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindBitmap;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import skin.support.SkinCompatManager;

public class MainActivity extends AppCompatActivity {
    private static final int WRITE_EXTERNAL_STORAGE = 0;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.imageView)
    ImageView imageView;

    @BindBitmap(R.mipmap.ic_launcher)
    public Bitmap bitmap;
    @BindView(R.id.button2)
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS}, WRITE_EXTERNAL_STORAGE);
        }
    }

    /**
     * 权限判断
     * @param context 上下文
     * @param permission 权限
     * @return boolean
     */
    public static boolean checkSelfPermission(Context context, String permission){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED;
    }

    /**
     * @param skinName CustomSDCardLoader中设置的SD卡目录下的skin文件
     */
    private void loadSkin(String skinName) {
        SkinCompatManager.getInstance().loadSkin(skinName, new SkinCompatManager.SkinLoaderListener() {
            @Override
            public void onStart() {
                Log.d("skin", "onStart");

            }

            @Override
            public void onSuccess() {
                Log.d("skin", "onSuccess");

            }

            @Override
            public void onFailed(String errMsg) {
                Log.d("skin", "onFailed: " + errMsg);

            }
        }, CustomSDCardLoader.SKIN_LOADER_STRATEGY_SDCARD);//皮肤策略：就是从哪儿加载皮肤。如：assets/skins 和 SD卡中.
    }

    private void restoreDefaultTheme() {
        SkinCompatManager.getInstance().restoreDefaultTheme();
    }

    @OnClick(R.id.button)
    public void doButton() {
        loadSkin("fen.skin");
    }

    @OnClick(R.id.button2)
    public void doButton2() {
        restoreDefaultTheme();
    }
}
