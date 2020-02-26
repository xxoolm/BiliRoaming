package me.iacn.biliroaming.hook;

/**
 * Created by iAcn on 2019/3/27
 * Email i@iacn.me
 */
public abstract class BaseHook {

    ClassLoader mClassLoader;

    public BaseHook(ClassLoader classLoader) {
        this.mClassLoader = classLoader;
    }

    public abstract void startHook();
}