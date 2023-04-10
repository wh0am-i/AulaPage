package com.example.aulapage;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Adaptador extends FragmentStateAdapter {

    public Adaptador(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new Segundo();
            case 2:
                return new Terceiro();
            case 3:
                return new Quarto();
        }
        return new Primeiro();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
