package mash.pregnency;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Detaill extends AppCompatActivity {
    TextView tv1;
    Context context;
    Element element2;
    Button but;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public String dec,sym,belly, msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaill);
        context=Detaill.this;
        toolbar=(Toolbar)findViewById(R.id.tool);
        tv1=(TextView)findViewById(R.id.textdate);
        //tv1.setText("Hello");

        String value = getIntent().getExtras().getString("week");
        msg=FileHandling.getSelectedWeek(value);
        tv1.setText("Week "+value+"\n"+msg);
        try {

            InputStream is = getAssets().open("file.xml");
            dec=FileHandling.getCurrentDesc(is, value);
            sym=FileHandling.getCurrentSymp();
            belly=FileHandling.getCurrentBelly();
        }

        catch (Exception e) {
                e.printStackTrace();
        }
        //      Tabbed View
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.viewpager1);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs1);
        tabLayout.setupWithViewPager(viewPager);
//       tabbed view end
    }
    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
    //TabbedView Declaration
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            Intent i =new Intent(Tabbs.this,Stopwatch.class);
            startActivity(i);
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }*/
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(dec), "Description");
        adapter.addFragment(new TwoFragment(sym), "Symptoms");
        adapter.addFragment(new ThreeFragment(belly), "Belly");
        //Toast.makeText(context, "belly" + bellyy, Toast.LENGTH_LONG).show();
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
