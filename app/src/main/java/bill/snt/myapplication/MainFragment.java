package bill.snt.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainFragment extends Fragment {

    private TabLayout tabLayout;
    private RecyclerView headerAppsRecyclerView;
    private RecyclerView allAppsRecyclerView;
    private RecyclerView workAppsRecyclerView;

    private AppsAdapter headerAppsAdapter;
    private AppsAdapter allAppsAdapter;
    private AppsAdapter workAppsAdapter;

    public MainFragment() {

    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        headerAppsAdapter = new AppsAdapter(getContext(), Arrays.asList(
                "GMail",
                "Google",
                "Play Store",
                "Youtube"
        ));

        allAppsAdapter = new AppsAdapter(
                getContext(),
                getBigAppList()
        );

        workAppsAdapter = new AppsAdapter(
                getContext(),
                getSmallAppList()
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main, container, false);
        findViews(v);

        headerAppsRecyclerView.setLayoutManager(getLayoutManager());
        headerAppsRecyclerView.setAdapter(headerAppsAdapter);
        allAppsRecyclerView.setLayoutManager(getLayoutManager());
        allAppsRecyclerView.setAdapter(allAppsAdapter);
        workAppsRecyclerView.setLayoutManager(getLayoutManager());
        workAppsRecyclerView.setAdapter(workAppsAdapter);

        if (true) { // true = WORK PROFILE ACTIVATED
            workAppsRecyclerView.setVisibility(View.GONE);
            tabLayout.addOnTabSelectedListener(getTabSelectedListener());
        } else {
            tabLayout.setVisibility(View.GONE);
            workAppsRecyclerView.setVisibility(View.GONE);
            // TODO show divider here
        }
        return v;
    }

    @NonNull
    private TabLayout.OnTabSelectedListener getTabSelectedListener() {
        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        workAppsRecyclerView.setVisibility(View.GONE);
                        allAppsRecyclerView.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        allAppsRecyclerView.setVisibility(View.GONE);
                        workAppsRecyclerView.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
    }

    private void findViews(View v) {
        tabLayout = v.findViewById(R.id.tab_layout);
        allAppsRecyclerView = v.findViewById(R.id.apps_recycler_view);
        workAppsRecyclerView = v.findViewById(R.id.work_recycler_view);
        headerAppsRecyclerView = v.findViewById(R.id.header_apps_recyclerview);
    }

    @NonNull
    private GridLayoutManager getLayoutManager() {
        return new GridLayoutManager(
                getContext(),
                4,
                LinearLayoutManager.VERTICAL,
                false);
    }

    private List<String> getBigAppList() {
        List<String> largeList = new ArrayList<>();
        for (int i = 0; i < 81; i++) {
            largeList.add("My App " + i);
        }
        return largeList;
    }

    private List<String> getSmallAppList() {
        List<String> smallList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            smallList.add("Work App " + i);
        }
        return smallList;
    }
}