package com.example.a3130project.Fragments;

import android.os.Bundle;

import com.example.a3130project.R;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a3130project.Adapters.SectionsPagerAdapter;

public class MedTabFragment extends Fragment
{
	@Nullable
	@Override
	public View onCreateView(
			@NonNull LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment_med_tabs, container, false);
	}


	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getActivity(),
		                                                                     getActivity().getSupportFragmentManager());
		ViewPager viewPager = getActivity().findViewById(R.id.view_pager);
		viewPager.setAdapter(sectionsPagerAdapter);
		TabLayout tabs = getActivity().findViewById(R.id.tabs);
		tabs.setupWithViewPager(viewPager);
	}

}