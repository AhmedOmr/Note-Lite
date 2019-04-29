package com.mecodroid.notelite;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.Bidi;
import java.util.List;
import java.util.Locale;

class RvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    CardView card;

    private final Context context;
    private List<DataNote> notelist;
    Typeface font1, font2, font3, font4;
    EmptyHolder Emholder;
    viewholder holder;
    private static final int VIEW_TYPE_EMPTY_LIST_PLACEHOLDER = 0;
    private static final int VIEW_TYPE_OBJECT_VIEW = 1;
    public RvAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        font1 = Typeface.createFromAsset(context.getAssets(), "ae_Cortoba.ttf");
        font2 = Typeface.createFromAsset(context.getAssets(), "arabtype.ttf");
        font3 = Typeface.createFromAsset(context.getAssets(), "CairoExtended Regular.ttf");
        font4 = Typeface.createFromAsset(context.getAssets(), "LateefRegOT.ttf");
        if (viewType == VIEW_TYPE_OBJECT_VIEW) {
            View v1 = LayoutInflater.from(context).inflate(R.layout.row_items, parent, false);
            return new viewholder(v1);
        }
        else {
            View v2 = LayoutInflater.from(context).inflate(R.layout.row_empty, parent, false);
            return new EmptyHolder(v2);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vholder, int postion) {
        switch(getItemViewType(postion)) {
            case VIEW_TYPE_EMPTY_LIST_PLACEHOLDER:
                // return view holder for your placeholder
                Emholder = (EmptyHolder) vholder;
                Emholder.texempty.setTextSize(36);
                break;
            case VIEW_TYPE_OBJECT_VIEW:
                // return view holder for your normal list item
                DataNote dataNote = notelist.get(postion);
                holder = (viewholder) vholder;
                Bidi bidi = new Bidi(notelist.get(postion).getTitle(), Bidi.DIRECTION_DEFAULT_LEFT_TO_RIGHT);
                Bidi bidi2 = new Bidi(notelist.get(postion).getSubject(), Bidi.DIRECTION_DEFAULT_LEFT_TO_RIGHT);
                if (bidi.getBaseLevel() == 0) {
                    holder.tex3.setGravity(Gravity.LEFT);
                    holder.tex3.setTypeface(font3);
                } else {
                    holder.tex3.setGravity(Gravity.RIGHT);
                    holder.tex3.setTypeface(font1);

                }
                if (bidi2.getBaseLevel() == 0) {
                    holder.tex4.setGravity(Gravity.LEFT);
                    holder.tex4.setTypeface(font2);


                } else {
                    holder.tex4.setGravity(Gravity.RIGHT);
                    holder.tex4.setTypeface(font2);
                }

                holder.tex3.setText(dataNote.getTitle());
                holder.tex4.setText(dataNote.getSubject());
                holder.tex6.setImageResource(dataNote.getPrcolor());
                if (Locale.getDefault().getLanguage().equals("ar")) {
                    holder.tex5.setText(dataNote.getShowdateAr());
                    holder.tex5.setTypeface(font4);

                } else {
                    holder.tex5.setText(dataNote.getShowdateEn());
                    holder.tex5.setTypeface(font4);

                }
                break;
        }
        /* This class has getBaseLevel() method
         which returns 0 if your text is left-to-right otherwise 1 (if right-to-left).*/

    }


        @Override
        public int getItemCount() {
            return (notelist == null || notelist.isEmpty()) ? 1 : notelist.size();
        }
    @Override
    public int getItemViewType(int position) {
        if (notelist == null) {
            return VIEW_TYPE_EMPTY_LIST_PLACEHOLDER;
        } else {
            return VIEW_TYPE_OBJECT_VIEW;
        }
    }

    public void setDatanote(List<DataNote> noteList) {
        this.notelist = noteList;

    }


    class viewholder extends RecyclerView.ViewHolder {
        TextView tex3, tex4, tex5;
        ImageView tex6;

        public viewholder(View itemView) {
            super(itemView);
            tex3 = itemView.findViewById(R.id.textview3);
            tex4 = itemView.findViewById(R.id.textview4);
            tex5 = itemView.findViewById(R.id.textview5);
            tex6 = itemView.findViewById(R.id.imaprior);
            card = itemView.findViewById(R.id.cardview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sendnote = new Intent(context, Edit_Note.class);
                    sendnote.putExtra("note", notelist.get(getAdapterPosition()));
                    context.startActivity(sendnote);
                }
            });
        }
    }
    class EmptyHolder extends RecyclerView.ViewHolder{
    TextView texempty;
    public EmptyHolder(@NonNull View itemView) {
            super(itemView);
            texempty = itemView.findViewById(R.id.textempty);
        }
    }
}
