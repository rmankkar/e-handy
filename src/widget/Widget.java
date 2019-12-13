package widget;
import it.converter.Main;

import com.Ehandy.Quizzer.SplashActivity;
import com.gps.demo.GPSActivity;

import learn.student.LearnActivity;
import main.GalleryActivity;
import main.SocialMedia;
import rss.feed.FeedTabActivity;
import temp.test.TempActivity;
import eHandy.gtbit.R;
import gtbit.course.CourseActivity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class Widget extends AppWidgetProvider {

	//private static final String ACTION_CLICK = "ACTION_CLICK";
	

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		
		
		// Get all ids
		ComponentName thisWidget = new ComponentName(context, Widget.class);
		int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
		for (int widgetId : allWidgetIds) {
			Intent intent1 = new Intent(context, FeedTabActivity.class);
			intent1.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			//intent.setAction(ACTION_CLICK);
			PendingIntent pendIntent1 = PendingIntent.getActivity(context, 0,intent1, PendingIntent.FLAG_UPDATE_CURRENT);

			Intent intent2 = new Intent(context, CourseActivity.class);
			//intent.setAction(ACTION_CLICK);
			PendingIntent pendIntent2 = PendingIntent.getActivity(context, 0,intent2, PendingIntent.FLAG_UPDATE_CURRENT);

			Intent intent3 = new Intent(context, LearnActivity.class);
			//intent.setAction(ACTION_CLICK);
			PendingIntent pendIntent3 = PendingIntent.getActivity(context, 0,intent3, PendingIntent.FLAG_UPDATE_CURRENT);

			Intent intent4 = new Intent(context, SplashActivity.class);
			//intent.setAction(ACTION_CLICK);
			PendingIntent pendIntent4 = PendingIntent.getActivity(context, 0,intent4, PendingIntent.FLAG_UPDATE_CURRENT);

			Intent intent5 = new Intent(context, TempActivity.class);
			//intent.setAction(ACTION_CLICK);
			PendingIntent pendIntent5 = PendingIntent.getActivity(context, 0,intent5, PendingIntent.FLAG_UPDATE_CURRENT);

			Intent intent6 = new Intent(context, GalleryActivity.class);
			//intent.setAction(ACTION_CLICK);
			PendingIntent pendIntent6 = PendingIntent.getActivity(context, 0,intent6, PendingIntent.FLAG_UPDATE_CURRENT);

			Intent intent7 = new Intent(context, Main.class);
			//intent.setAction(ACTION_CLICK);
			PendingIntent pendIntent7 = PendingIntent.getActivity(context, 0,intent7, PendingIntent.FLAG_UPDATE_CURRENT);

			Intent intent8 = new Intent(context, SocialMedia.class);
			//intent.setAction(ACTION_CLICK);
			PendingIntent pendIntent8 = PendingIntent.getActivity(context, 0,intent8, PendingIntent.FLAG_UPDATE_CURRENT);

			Intent intent9 = new Intent(context, GPSActivity.class);
			//intent.setAction(ACTION_CLICK);
			PendingIntent pendIntent9 = PendingIntent.getActivity(context, 0,intent9, PendingIntent.FLAG_UPDATE_CURRENT);

			RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.widgetmenumod);
			views.setOnClickPendingIntent(R.id.widget_rss, pendIntent1);
			views.setOnClickPendingIntent(R.id.widget_course, pendIntent2);
			views.setOnClickPendingIntent(R.id.widget_learn, pendIntent3);
			views.setOnClickPendingIntent(R.id.widget_quiz, pendIntent4);
			views.setOnClickPendingIntent(R.id.widget_plcmnt, pendIntent5);
			views.setOnClickPendingIntent(R.id.widget_gallery, pendIntent6);
			views.setOnClickPendingIntent(R.id.widget_unit, pendIntent7);
			views.setOnClickPendingIntent(R.id.widget_share, pendIntent8);
			views.setOnClickPendingIntent(R.id.widget_map, pendIntent9);

			
			
			appWidgetManager.updateAppWidget(widgetId, views);
		}
	}
}