package sachan.dheeraj.fragmenttester;

import android.app.Application;
import android.util.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dheeraj on 4/5/15.
 */

public class MainApplication extends Application {
    private static final String TAG = MainApplication.class.getSimpleName();
    private static final Logger LOGGER = LoggerFactory.getLogger(MainApplication.class);

    @Override
    public void onCreate() {
        super.onCreate();
        final Thread.UncaughtExceptionHandler uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                /*File internalCard = Environment.getExternalStorageDirectory();
                String state = Environment.getExternalStorageState();
                if (internalCard.exists() && internalCard.canWrite() && Environment.MEDIA_MOUNTED.equals(state) && !Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
                    File file = new File(internalCard,"superProfs_errorLog.txt");
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(file,true);
                        PrintStream printStream = new PrintStream(fileOutputStream);
                        String s = "------------------------------------------------------------------------------------------------------------\n";
                        fileOutputStream.write(s.getBytes());
                        ex.printStackTrace(printStream);
                    }catch (Exception e){}
                }*/

           /*     final StringWriter sw = new StringWriter();
                final PrintWriter pw = new PrintWriter(sw, true);
                pw.write("------------------------------------------------------------");
                ex.printStackTrace(pw);
                String data = sw.getBuffer().toString();*/
                LOGGER.error("error",ex);
                LOGGER.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                LOGGER.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                LOGGER.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                LOGGER.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                LOGGER.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                LOGGER.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                LOGGER.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                LOGGER.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                LOGGER.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                LOGGER.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                Log.e(TAG, ex.getLocalizedMessage());
                Log.d(TAG, "*****************************************************************");
                Log.d(TAG,"*****************************************************************");
                Log.d(TAG,"*****************************************************************");
                Log.d(TAG,"*****************************************************************");
                uncaughtExceptionHandler.uncaughtException(thread, ex);
            }
        });
    }
}

