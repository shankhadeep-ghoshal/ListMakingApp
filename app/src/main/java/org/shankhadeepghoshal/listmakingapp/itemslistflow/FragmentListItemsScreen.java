package org.shankhadeepghoshal.listmakingapp.itemslistflow;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;
import org.shankhadeepghoshal.listmakingapp.R;

import butterknife.BindView;

public class FragmentListItemsScreen extends Fragment {

    @BindView(R.id.TVSearchItem)
    EditText searchItemTV;
    @BindView(R.id.RVListOfItems)
    RecyclerView itemsListRV;
    @BindView(R.id.SortItems)
    Spinner sortItemSpinner;
    @BindView(R.id.GroupItems)
    Spinner groupItemSpinner;
    @BindView(R.id.FABAddNewItemToList)
    FloatingActionButton addNewItemToListFAB;

    public FragmentListItemsScreen() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_items_screen, container, false);
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
