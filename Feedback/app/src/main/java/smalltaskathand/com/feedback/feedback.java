package smalltaskathand.com.feedback;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class feedback extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Intent in=getIntent();
        try {
            final JSONObject j=new JSONObject(in.getStringExtra(ResultActivity.EXTRA_MESSAGE));
            TextView t=(TextView)findViewById(R.id.textView);
            t.setText("Enter feedback for " + j.getString("id"));
            Button b=(Button)findViewById(R.id.send);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText e=(EditText)findViewById(R.id.editText);
                    String feed=  e.getText().toString();
                    JSONObject k=j;
                    try {
                        k.put("feedback", feed);
                      //  ProgressDialog p=ProgressDialog.show()
                        connection c= new connection();
                        c.push(k);
                        Toast.makeText(getApplicationContext(),"feedback was sent",Toast.LENGTH_SHORT).show();
                        finish();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });

        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feedback, menu);
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
