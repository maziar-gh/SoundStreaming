package sc.mp3musicplayer.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import java.lang.ref.WeakReference;

import sc.mp3musicplayer.constants.Constants;
import sc.mp3musicplayer.mpservice.MediaPlayerConnection;
import sc.mp3musicplayer.mpservice.MediaPlayerService;

public class ServiceHelper {

    /**
     * Binds the service {@link MediaPlayerService } to the main activity
     * @param mContext {@link sc.mp3musicplayer.ui.activities.MainActivity}.
     */
    public static void doBindService(WeakReference<Context> mContext, MediaPlayerConnection mConnection){
        if((!mConnection.isBind()) && (mContext.get() != null) ){
            Intent mIntent = new Intent(mContext.get(), MediaPlayerService.class);
            mContext.get().bindService(mIntent, mConnection, Context.BIND_AUTO_CREATE);
            mConnection.setIsBind(true);
        }
    }

    /**
     * Unbinds the service to the main activity.
     * @param mContext {@link sc.mp3musicplayer.ui.activities.MainActivity}.
     */
    public static void doUnBindService(WeakReference<Context> mContext, MediaPlayerConnection mConnection){
        if(mConnection.isBind() && mContext.get() != null){
            mContext.get().unbindService(mConnection);
            mConnection.setIsBind(false);
        }
    }

    /**
     * Starts the service {@link MediaPlayerService}
     * @param mActivity The view where the service has been started
     * @param position The track's position which will be played.
     */
    public static void startService(final WeakReference<Activity> mActivity, final int position){
        if(mActivity.get() != null){
            final Intent mIntent = new Intent(mActivity.get(), MediaPlayerService.class);
            mIntent.putExtra(Constants.TRACK_POSITION, String.valueOf(position));
            mActivity.get().startService(mIntent);
        }
    }

    /**
     * Stops the service {@link MediaPlayerService}
     * @param mContext {@link sc.mp3musicplayer.ui.activities.MainActivity}.
     */
    public static void doStopService(WeakReference<Context> mContext){
        if(mContext.get() != null) {
            mContext.get().stopService(new Intent(mContext.get(), MediaPlayerService.class));
        }
    }
}
