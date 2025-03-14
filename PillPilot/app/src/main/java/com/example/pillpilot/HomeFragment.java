package com.example.pillpilot;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pillpilot.databinding.FragmentHomeBinding;
import com.example.pillpilot.ui.main.MainViewModel;
import com.example.pillpilot.ui.main.PillListAdapter;

import java.util.List;
import java.util.Locale;
import androidx.recyclerview.widget.RecyclerView;



public class HomeFragment extends Fragment {

    private MainViewModel mViewModel;
    private FragmentHomeBinding binding;
    private PillListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onViewCreated(View view,@Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        listenerSetup();
        observerSetup();
        recyclerSetup();
    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }*/
    private void listenerSetup() {
        binding.addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.pillName.getText().toString();
                String date = binding.pillDate.getText().toString();
                String time = binding.pillTime.getText().toString();
                if (!name.equals("") && !date.equals("") && !time.equals("")) {
                    Pill pill = new Pill(name, date, time);
                    mViewModel.insertPill(pill);
                    clearFields();//miután elküldtük a Viewmodelnek töröljük a mezőket hogy ne ismétlődő rekordokat vigyünk be
                }//if itt lehetne üzenni a felhasználónak egy TextView-val
                //binding.messageTextView.setText("Hiányos információ");
            } //onclick


        });//addbutton


        binding.findbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.findPill(binding.pillName.getText().toString());
            }
        });//findButton


        binding.deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.deletePill(binding.pillName.getText().toString());
                clearFields();
            }
        });//deletebutton

    }//listenersetup

    private void observerSetup() {
        mViewModel.getAllPills().observe(getViewLifecycleOwner(),
                new Observer<List<Pill>>() {
                    @Override
                    public void onChanged(@Nullable final List<Pill> pills) {
                        adapter.setPillList(pills);
                    }
                });
        mViewModel.getSearchResults().observe(getViewLifecycleOwner(),
                new Observer<List<Pill>>() {
                    @Override
                    public void onChanged(@Nullable final List<Pill> pills) {
                        if (pills.size() > 0) { //binding.messageTextView.setText(String.format(Locale.US, "%d", pills.get(0).getId()));
                            binding.pillName.setText(pills.get(0).getName());
                            binding.pillName.setText(String.format(Locale.US,
                                    pills.get(0).getName()));
                        } //else {
                           // binding.messageTextView.setText("Nincs találat");   messageTextView lehetne egy üzenetablak a felhasználónak
                        //}
                    }
                });
    }//observersetup
    private void recyclerSetup() {
        adapter = new PillListAdapter(R.layout.pill_list_item);
        binding.dataRecycler.setLayoutManager(
                new LinearLayoutManager(getContext()));
        binding.dataRecycler.setAdapter(adapter);
    }


    private void clearFields() {
        binding.pillName.setText("");
        binding.pillDate.setText("");
        binding.pillTime.setText("");
    }
}//fragment
