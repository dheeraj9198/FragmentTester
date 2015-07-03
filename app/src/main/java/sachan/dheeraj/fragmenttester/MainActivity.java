package sachan.dheeraj.fragmenttester;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.MediaController;

public class MainActivity extends ActionBarActivity {
    private static final String IP = "192.168.1.15";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    private  static boolean change = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          View decorView = getWindow().getDecorView();

        View view = findViewById(R.id.container1);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                new AsyncTask<Void,Void,Void>(){
                    @Override
                    protected void onPreExecute() {
                        v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        v.setSystemUiVisibility(/*View.SYSTEM_UI_FLAG_LAYOUT_STABLE |*/ View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                    }

                    @Override
                    protected Void doInBackground(Void... params) {
                        try{
                            Thread.sleep(2000);
                        }catch (Exception e){}
                        return null;
                    }
                }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                return true;
            }
        });

        if(change){
            change = false;
            getSupportActionBar().hide();

            WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dimension = new DisplayMetrics();
            Point point = new Point();
            windowManager.getDefaultDisplay().getRealSize(point);
            ViewGroup.LayoutParams layoutParams11 = view.getLayoutParams();
            layoutParams11.width = point.x;
            layoutParams11.height = point.y;

            view.setSystemUiVisibility(/*View.SYSTEM_UI_FLAG_LAYOUT_STABLE |*/ View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }else{
            change = true;
            getSupportActionBar().show();
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }
        //if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container1, new PlaceholderFragment1())
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container2, new PlaceholderFragment2())
                    .commit();
      /*  }else{
            Log.e("","");
        }*/
    }



    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onPostResume(){
        super.onPostResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment2 extends Fragment implements
            MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener,
            MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback, MediaController.MediaPlayerControl {

        private static final String TAG = PlaceholderFragment2.class.getSimpleName();
        private int mVideoWidth;
        private int mVideoHeight;
        private MediaPlayer mMediaPlayer;
        private SurfaceView surfaceView;
        private SurfaceHolder holder;
        private String path;
        private Bundle extras;
        private static final String MEDIA = "media";
        private static final int LOCAL_AUDIO = 1;
        private static final int STREAM_AUDIO = 2;
        private static final int RESOURCES_AUDIO = 3;
        private static final int LOCAL_VIDEO = 4;
        private static final int STREAM_VIDEO = 5;
        private boolean mIsVideoSizeKnown = false;
        private boolean mIsVideoReadyToBePlayed = false;

        public PlaceholderFragment2() {
        }


        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            Log.e("", "");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main2, container, false);
            surfaceView = (SurfaceView) rootView.findViewById(R.id.id1);
            holder = surfaceView.getHolder();
            holder.addCallback(this);
            holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
            return rootView;
        }

        public void onBufferingUpdate(MediaPlayer arg0, int percent) {
            Log.d(TAG, "onBufferingUpdate percent:" + percent);
        }

        public void onCompletion(MediaPlayer arg0) {
            Log.d(TAG, "onCompletion called");
        }

        public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
            Log.v(TAG, "onVideoSizeChanged called");
            if (width == 0 || height == 0) {
                Log.e(TAG, "invalid video width(" + width + ") or height(" + height
                        + ")");
                return;
            }
            mIsVideoSizeKnown = true;
            mVideoWidth = width;
            mVideoHeight = height;
            if (mIsVideoReadyToBePlayed && mIsVideoSizeKnown) {
                startVideoPlayback();
            }
        }

        public void onPrepared(MediaPlayer mediaplayer) {
            Log.d(TAG, "onPrepared called");
            mIsVideoReadyToBePlayed = true;
            if (mIsVideoReadyToBePlayed && mIsVideoSizeKnown) {
                startVideoPlayback();
            }
        }

        public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k) {
            Log.d(TAG, "surfaceChanged called");

        }

        public void surfaceDestroyed(SurfaceHolder surfaceholder) {
            Log.d(TAG, "surfaceDestroyed called");
        }

        public void surfaceCreated(SurfaceHolder holder) {
            Log.d(TAG, "surfaceCreated called");
/*
            playVideo();
*/
        }

        private void playVideo() {
            mMediaPlayer = new MediaPlayer();
            try {
/*
                mMediaPlayer.setDataSource("http://content.jwplatform.com/manifests/vM7nH0Kl.m3u8");
*/
                mMediaPlayer.setDataSource("http://" + IP + ":1935/vod/mp4:lynda.mp4/playlist.m3u8");
                mMediaPlayer.setDisplay(holder);
                mMediaPlayer.prepare();
                mMediaPlayer.setOnBufferingUpdateListener(this);
                mMediaPlayer.setOnCompletionListener(this);
                mMediaPlayer.setOnPreparedListener(this);
                mMediaPlayer.setOnVideoSizeChangedListener(this);
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                final MediaController mcontroller = new MediaController(getActivity());
                mcontroller.setMediaPlayer(this);
                mcontroller.setAnchorView(surfaceView);
                mcontroller.setEnabled(true);
                mcontroller.show();

                surfaceView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mcontroller.show();
                    }
                });


            } catch (Exception e) {
                Log.e("", "", e);
            }
        }


        @Override
        public void onPause() {
            super.onPause();
            releaseMediaPlayer();
            doCleanUp();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            releaseMediaPlayer();
            doCleanUp();
        }

        private void releaseMediaPlayer() {
            if (mMediaPlayer != null) {
                mMediaPlayer.release();
                mMediaPlayer = null;
            }
        }

        private void doCleanUp() {
            mVideoWidth = 0;
            mVideoHeight = 0;
            mIsVideoReadyToBePlayed = false;
            mIsVideoSizeKnown = false;
        }

        private void startVideoPlayback() {
            Log.v(TAG, "startVideoPlayback");
            holder.setFixedSize(mVideoWidth, mVideoHeight);
            mMediaPlayer.start();
        }

        @Override
        public void start() {

        }

        @Override
        public void pause() {

        }

        @Override
        public int getDuration() {
            return 0;
        }

        @Override
        public int getCurrentPosition() {
            return mMediaPlayer.getCurrentPosition();
        }

        @Override
        public void seekTo(int pos) {
            int k = mMediaPlayer.getCurrentPosition();
            mMediaPlayer.seekTo(pos);
        }

        @Override
        public boolean isPlaying() {
            return false;
        }

        @Override
        public int getBufferPercentage() {
            return 0;
        }

        @Override
        public boolean canPause() {
            return false;
        }

        @Override
        public boolean canSeekBackward() {
            return true;
        }

        @Override
        public boolean canSeekForward() {
            return true;
        }

        @Override
        public int getAudioSessionId() {
            return 0;
        }

/*
            startActivityForResult(new Intent(android.provider.Settings.ACTION_DATE_SETTINGS), 11);
*/
        /*    new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {

                    try {
                        URL url = new URL("http://www.lmcbahrain.com/financials/87-fp_dc_setup_guide.pdf");
                        File file = new File("/mnt/extSdCard/delete/tmp.pdf");
                        downloadFile(url, file);
                    } catch (Exception e) {
                        Log.e("", e.getLocalizedMessage());
                        if(e instanceof IOException && e.getMessage().contains("ENOSPC")){
                            Log.e("","",e);
                        }else if(e instanceof FileNotFoundException && e.getMessage().contains("http")){
                            Log.e("","",e);
                        }
                    }
                    return null;
                }

                private void downloadFile(URL url, File file) throws Exception {
                    InputStream in = null;
                    FileOutputStream fileOutputStream = null;
                    try {
                        byte[] bytes = new byte[2048];
                        int k;
                        in = url.openStream();
                        fileOutputStream = new FileOutputStream(file);
                        while ((k = in.read(bytes)) != -1) {
                            fileOutputStream.write(bytes, 0, k);
                        }
                    } catch (Exception e) {
                        throw e;
                    } finally {
                        try {
                            if (in != null) {
                                in.close();
                            }
                        } catch (Exception e1) {
                        }
                        try {
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        } catch (Exception e1) {
                        }
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);*/
    }
}
