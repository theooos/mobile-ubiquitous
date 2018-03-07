package red.theodo.restauranthygienechecker1453831;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ListFragment extends android.app.ListFragment {

    private static final String ARG_PARAM1 = "param1";

    private ArrayList<Establishment> searchResults;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static ListFragment newInstance(ArrayList<Establishment> searchResults) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, searchResults);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            searchResults = getArguments().getParcelableArrayList(ARG_PARAM1);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter establishmentsAdpt;
        if (searchResults != null) {
            establishmentsAdpt = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, searchResults);
        } else {
            establishmentsAdpt = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, new ArrayList<>());
        }
        setListAdapter(establishmentsAdpt);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        System.out.println(searchResults.get(position));
    }
}
