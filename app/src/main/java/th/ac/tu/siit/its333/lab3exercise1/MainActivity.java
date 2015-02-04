package th.ac.tu.siit.its333.lab3exercise1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    int cr = 0;         // Credits
    double gp = 0.0;    // Grade points
    double gpa = 0.0;   // Grade point average

    List<String> listCodes;
    List<Integer> listCredits;
    List<Double> listGrades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCodes = new ArrayList<String>();
        listCredits = new ArrayList<Integer>();
        listGrades = new ArrayList<Double>();

        //Use listCodes.add("ITS333"); to add a new course code
        //Use listCodes.size() to refer to the number of courses in the list
    }

    public void calculateGPA()
    {
         cr = 0;
         gp = 0.0;
         gpa = 0.0;
        int i;

        for(i=0;i<listCodes.size();i++)
        {
            cr += listCredits.get(i);
            gp += listGrades.get(i)*listCredits.get(i);
        }
        gpa = gp/cr;

        TextView tvGP = (TextView)findViewById(R.id.tvGP);
        TextView tvCR = (TextView)findViewById(R.id.tvCR);
        TextView tvGPA = (TextView)findViewById(R.id.tvGPA);

        tvGP.setText(Integer.toString(cr));
        tvCR.setText(Double.toString(gp));
        tvGPA.setText(Double.toString(gpa));
    }

    public void AddCourseClicked(View v)
    {
            Intent i = new Intent(this,CourseActivity.class);
            startActivityForResult(i, 21);
    }

    public void CourseListClicked(View v)
    {
        Intent i = new Intent(this,CourseListActivity.class);
        String s = "List of Courses\n";

        for(int x=0;x<listCodes.size();x++)
        {
            String credit = Integer.toString(listCredits.get(x));
            String grade = new String();
            if(listGrades.get(x) == 4.0)
                grade = "A";
            if(listGrades.get(x) == 3.5)
                grade = "B+";
            if(listGrades.get(x) == 3.0)
                grade = "B";
            if(listGrades.get(x) == 2.5)
                grade = "C+";
            if(listGrades.get(x) == 2.0)
                grade = "C";
            if(listGrades.get(x) == 1.5)
                grade = "D+";
            if(listGrades.get(x) == 1.0)
                grade = "D";
            if(listGrades.get(x) == 0.0)
                grade = "F";

            String result = listCodes+" ("+credit+" Credit) = "+grade+"\n";
            s = s+result;
        }
        i.putExtra("list",s);
        startActivity(i);

    }

    public void ResetClicked(View v)
    {
        cr = 0;         // Credits
        gp = 0.0;    // Grade points
        gpa = 0.0;   // Grade point average

        listCodes = new ArrayList<String>();
        listCredits = new ArrayList<Integer>();
        listGrades = new ArrayList<Double>();

        TextView tvGP = (TextView)findViewById(R.id.tvGP);
        TextView tvCR = (TextView)findViewById(R.id.tvCR);
        TextView tvGPA = (TextView)findViewById(R.id.tvGPA);

        tvGP.setText(Double.toString(gp));
        tvCR.setText(Integer.toString(cr));
        tvGPA.setText(Double.toString(gpa));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Values from child activity
        TextView tvGP = (TextView)findViewById(R.id.tvGP);
        TextView tvCR = (TextView)findViewById(R.id.tvCR);
        TextView tvGPA = (TextView)findViewById(R.id.tvGPA);
        if(requestCode == 21)
        {
            if(resultCode == RESULT_OK)
            {
                String CourseCode = data.getStringExtra("CourseCode");
                int Credit = data.getIntExtra("Credit",0);
                Double Grade = data.getDoubleExtra("Grade",0);

                listCodes.add(CourseCode);
                listCredits.add(Credit);
                listGrades.add(Grade);

                calculateGPA();
            }

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
