package com.example.gocars.ui.MyBooking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gocars.CustomAdapter.BookNow_Rec;
import com.example.gocars.R;
import com.example.gocars.faltu_context;
import com.example.gocars.ui.Change_City.CityViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyBookingFragment extends Fragment {
    RecyclerView recyclerView;
    private MyBookingViewModel myBookingViewModel;
    private ArrayList<String> nNames=new ArrayList<>();
    private ArrayList<String> mImagesURLs=new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myBookingViewModel =
                ViewModelProviders.of(this).get(MyBookingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_my_booking, container, false);
        //final TextView textView = root.findViewById(R.id.text_mybooking);

      recyclerView=root.findViewById(R.id.recycleview);
        myBookingViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);

            }
        });
        initImageBitmaps();
        return root;
    }

    private void initImageBitmaps() {
        mImagesURLs.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        nNames.add("Havasu Falls");

        mImagesURLs.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        nNames.add("Trondheim");

        mImagesURLs.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        nNames.add("Portugal");
        initRecylerView();
    }

    private void initRecylerView() {

        BookNow_Rec adapter=new BookNow_Rec(nNames,mImagesURLs,faltu_context.context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(faltu_context.context));
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(faltu_context.context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
    }
}
