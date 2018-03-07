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

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//
//        final AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Establishment estab = (Establishment) establishmentsAdpt.getItem(i);
//                Intent intent = new Intent(getActivity(), DetailsActivity.class);
//                intent.putExtra("establishment", estab);
//                startActivity(intent);
//            }
//        };
//        establishmentsAdpt.notifyDataSetChanged();
//    }


//    private OnFragmentInteractionListener mListener;

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
