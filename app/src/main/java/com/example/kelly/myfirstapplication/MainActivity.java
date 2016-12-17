package com.example.kelly.myfirstapplication;
import java.io.*;
import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.view.*;
import android.graphics.*;
import android.widget.*;
import android.provider.*;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import com.microsoft.projectoxford.face.\*;
import com.microsoft.projectoxford.face.contract.\*;


public class MainActivity extends AppCompatActivity {
    private final int PICK_IMAGE = 1;
    private ProgressDialog detectionProgressDialog;
    private FaceServiceClient faceServiceClient =
            new FaceServiceRestClient("3289657d188b4159b39df028555c5a19");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallIntent = new Intent(Intent.ACTION_GET_CONTENT);
                gallIntent.setType("image/*");
                startActivityForResult(Intent.createChooser(gallIntent, "Select Picture"), PICK_IMAGE);
            }
        });

        detectionProgressDialog = new ProgressDialog(this);
        }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    ImageView imageView = (ImageView) findViewById(R.id.imageView1);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

private void detectAndFrame(final Bitmap imageBitmap)
{
    ByteArrayOutputStream outputStram = new ByteArrayOutputStream();
    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream());
    ByteArrayInputStream inputStream =
        new ByteArrayInputStream(outputStream.toByteArray());
    AsyncTask<InputStream, String, Face[]> detectTask =
    new AsyncTask<InputStream, String, Face[]>() {
         @Override
        protect Face[] doInBackground(InputStream... params) {
        try {
        publishProgress("Detecting...");
        Face[] results = faceServiceClient.detect(
                params[0],
                true
                false,
                null
            );
            if (result == null)
            {
                    publishProgress("detection finished: Nothing detected",
                    return null;
            }
            publishProgress(
                    String.format("Detection Finished. %d face(s) detected",
                            result.length));
            return results;

        }  catch (Exception e) {
            publishProgress("detection failed");
            return null;

        }

        }

        private static Bitman drawFacerectanglesOnBitmap(Bitmap origianakBitmap Face[] faces) {
                Bitmap bitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
            canvas canvas = new Canvas(bitmap);
            Paint point = new Paint();
            paint.setAntiAlias(true);
            paaint.setStyle(Paint.Style.STROKE);
            paint.SetColor(Color.Red);
            int stokeWidth = 2;
            paint.setStrokeWidth(stokeWidth);
            if (face != null){
                faceRectangle faceRectangle = face.faceRectangle:
                canvas.drawRect{
                    faceRectangle.left;
                    faceRectangle.top;
                    faceRectangle.left + faceRectangle.width,
                    faceRectangle.top + faceRectangle.height,
                    paint);

                }
            }
            return bitmap;
        }

    @Override
        protect void onPreExecute() {
            detectionProgressDialog.show();
        }
        @Override
        protected void onProgressUndate(String... progress) {
            detectionProgressDialog.setMessage(progress[0]);
        }
        @Overrideprotected void onPostExecute(Face[] results) {

            detectionPRogressDialog dismiss();
            if (result == null) return;
            ImageView imageView = (ImageView)findViewById(R.id.imageView1);
            imageView.setImageBitmap(drawFaceRectanglesOnBitmap(imageBitmap, result);
                    imageBitmap.recycle();
        }
    };
    detectTask.execute(inputStream);

    }

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


}
