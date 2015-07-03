package sachan.dheeraj.fragmenttester;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class PlaceholderFragment1 extends Fragment {

    private VideoView videoView;

    public PlaceholderFragment1() {
        super();
    }

    @Override
    public void setRetainInstance(boolean retain) {
        super.setRetainInstance(retain);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main1, container, false);
        videoView = (VideoView) rootView.findViewById(R.id.id);
        Button button = (Button) rootView.findViewById(R.id.play);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo();
            }
        });

         /*   try {
                Uri uri = Uri.parse("http://192.168.1.15:1935/vod/mp4:lynda.mp4/manifest.mpd");
                Intent shareIntent = new Intent(Intent.ACTION_VIEW, uri);
                shareIntent.setPackage("org.videolan.vlc");
                startActivity(shareIntent);
            } catch (Exception e) {
                Log.e("", "", e);
            }*/

        return rootView;
    }

    private void playVideo() {
        MediaController mediaControls = new MediaController(getActivity());
        //initialize the VideoView
        // create a progress bar while the video file is loading
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        // set a title for the progress bar
        progressDialog.setTitle("JavaCodeGeeks Android Video View Example");
        // set a message for the progress bar
        final long time1 = System.currentTimeMillis();
        progressDialog.setMessage("Loading...");
        //set the progress bar not cancelable on users' touch
        progressDialog.setCancelable(false);
        // show the progress bar
        progressDialog.show();

        try {
            //set the media controller in the VideoView
            videoView.setMediaController(mediaControls);

            //set the uri of the video to be played
            //videoView.setVideoURI(Uri.parse("http://54.243.144.35:1935/vod/_definst_/smil:tmp/0_9xum6cv2.smil/playlist.m3u8"));
            //videoView.setVideoURI(Uri.parse("http://content.jwplatform.com/manifests/vM7nH0Kl.m3u8"));
            //videoView.setVideoURI(Uri.parse("http://" + IP + ":1935/vod/mp4:lynda.mp4/playlist.m3u8"));
            videoView.setVideoURI(Uri.parse("http://mediaserver.superprofs.com:1935/vod-android/_definst_/mp4:amazons3/superprofs-media/private/lectures/12/12_960x540_200k.mp4/chunklist.m3u8"));

            videoView.requestFocus();
            //we also set an setOnPreparedListener in order to know when the video file is ready for playback
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mediaPlayer) {
                    // close the progress bar and play the video
                    Toast.makeText(getActivity(), String.valueOf((System.currentTimeMillis() - time1) / 1000), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    //if we have a position on savedInstanceState, the video playback should start from here
                    videoView.start();
                }
            });
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
    }

}

