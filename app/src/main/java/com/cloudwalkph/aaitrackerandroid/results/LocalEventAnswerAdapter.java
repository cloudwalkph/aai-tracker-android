package com.cloudwalkph.aaitrackerandroid.results;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cloudwalkph.aaitrackerandroid.R;
import com.cloudwalkph.aaitrackerandroid.lib.model.localEventAnswers.LocalEventAnswer;
import com.cloudwalkph.aaitrackerandroid.lib.model.localEventAnswers.LocalPollAnswer;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

/**
 * Created by trick.sunga on 08/12/2016.
 */

public class LocalEventAnswerAdapter extends RealmBasedRecyclerViewAdapter<LocalEventAnswer, LocalEventAnswerAdapter.ViewHolder> {

    public class ViewHolder extends RealmViewHolder {

        @BindView(R.id.age)
        TextView age;
        @BindView(R.id.gender)
        TextView gender;
        @BindView(R.id.picture)
        ImageView picture;

        public ViewHolder(LinearLayout container) {
            super(container);
            ButterKnife.bind(this, container);
        }
    }

    public LocalEventAnswerAdapter(
            Context context,
            RealmResults<LocalEventAnswer> realmResults,
            boolean automaticUpdate,
            boolean animateResults) {
        super(context, realmResults, automaticUpdate, animateResults);
    }

    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int viewType) {
        View v = inflater.inflate(R.layout.fragment_result_item, viewGroup, false);
        ViewHolder vh = new ViewHolder((LinearLayout) v);
        return vh;
    }

    @Override
    public void onBindRealmViewHolder(ViewHolder viewHolder, int position) {
        final LocalEventAnswer localEventAnswer = realmResults.get(position);
        File image = new File(localEventAnswer.origImage);
        Picasso.with(getContext())
                .load(image)
                .placeholder(R.drawable.camera)
                .error(R.drawable.camera)
                .resize(320, 480)
                .centerInside()
                .into(viewHolder.picture);
        Log.d("RECYCLER", localEventAnswer.origImage);

        for (LocalPollAnswer localPollAnswer : localEventAnswer.localPollAnswers) {
            if(localPollAnswer.pollId.equals("1")) {
                viewHolder.age.setText(localPollAnswer.value);
            } else {
                viewHolder.gender.setText(localPollAnswer.value);
            }
        }
    }
}
