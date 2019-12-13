package rss.feed.storage;

import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SharedPreferencesCompat {
	private static final Method sApplyMethod = findApplyMethod();

	private static Method findApplyMethod() {
		try {
			Class<SharedPreferences.Editor> cls = SharedPreferences.Editor.class;
			return cls.getMethod("apply");
		} catch (NoSuchMethodException unused) {
			
		}
		return null;
	}

	public static void apply(SharedPreferences.Editor editor) {
		if (sApplyMethod != null) {
			try {
				sApplyMethod.invoke(editor);
				return;
			} catch (InvocationTargetException unused) {
	
			} catch (IllegalAccessException unused) {
				
			}
		}
		editor.commit();
	}
}
