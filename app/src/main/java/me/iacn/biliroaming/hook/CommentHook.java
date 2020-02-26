package me.iacn.biliroaming.hook;

import android.util.Log;

import de.robv.android.xposed.XC_MethodReplacement;
import me.iacn.biliroaming.XposedInit;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static me.iacn.biliroaming.Constant.TAG;

/**
 * Created by iAcn on 2020/2/27
 * Email i@iacn.me
 */
public class CommentHook extends BaseHook {

    public CommentHook(ClassLoader classLoader) {
        super(classLoader);
    }

    @Override
    public void startHook() {
        if (!XposedInit.sPrefs.getBoolean("comment_floor", false)) return;
        Log.d(TAG, "startHook: Comment");

        findAndHookMethod("com.bilibili.app.comm.comment2.model.BiliCommentConfig",
                mClassLoader, "isShowFloor", XC_MethodReplacement.returnConstant(true));
    }
}